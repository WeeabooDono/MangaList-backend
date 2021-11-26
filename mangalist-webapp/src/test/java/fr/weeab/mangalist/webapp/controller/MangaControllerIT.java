package fr.weeab.mangalist.webapp.controller;

import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import fr.weeab.mangalist.core.repository.MangaRepository;
import fr.weeab.mangalist.core.transform.dto.GenreDTO;
import fr.weeab.mangalist.core.transform.dto.GenreSaveDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagedSearchDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagerDTO;
import fr.weeab.mangalist.webapp.context.AbstractIntegrationTest;
import fr.weeab.mangalist.webapp.utils.TestWebappUtils;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashSet;
import java.util.Set;

import static fr.weeab.mangalist.core.utils.TestCoreUtils.joinWithSlash;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:datasets/repository/manga_repository_test_before.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:datasets/repository/manga_repository_test_after.sql"}),
})
public class MangaControllerIT extends AbstractIntegrationTest {

    private static final String ENDPOINT = "/api/v1/mangas/";

    @Nested
    class GetManga {

        @Test
        void getMangasWithPager_shouldReturnTwoMangas() throws Exception {
            PagerDTO pager = new PagerDTO(0, 2);

            final MockHttpServletRequestBuilder request = get(ENDPOINT)
                    .param("page", pager.getPage().toString())
                    .param("pageSize", pager.getPageSize().toString());

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.items", Matchers.hasSize(2)))
                    .andExpect(jsonPath("$.total").value(3));
        }

        @Test
        void getMangasWithPager_shouldReturnAllMangas() throws Exception {
            PagerDTO pager = new PagerDTO(0, 9);

            final MockHttpServletRequestBuilder request = get(ENDPOINT)
                    .param("page", pager.getPage().toString())
                    .param("pageSize", pager.getPageSize().toString());

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.items", Matchers.hasSize(3)))
                    .andExpect(jsonPath("$.total").value(3));
        }

        @Test
        void getManga_shouldReturnAManga() throws Exception {
            final long id = 2L;
            final MockHttpServletRequestBuilder request = get(joinWithSlash(ENDPOINT, String.valueOf(id)));

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", equalTo(2)))
                    .andExpect(jsonPath("$.title", equalTo("test_2")))
                    .andExpect(jsonPath("$.description", equalTo("description")))
                    .andExpect(jsonPath("$.author", equalTo("author")))
                    .andExpect(jsonPath("$", not(hasKey("image"))))
                    .andExpect(jsonPath("$", not(hasKey("entityVersion"))))
                    .andExpect(jsonPath("$", not(hasKey("createdBy"))))
                    .andExpect(jsonPath("$", not(hasKey("createdDate"))))
                    .andExpect(jsonPath("$", not(hasKey("lastModifiedDate"))))
                    .andExpect(jsonPath("$", not(hasKey("lastModifiedBy"))));
        }

        @Test
        void getManga_shouldReturnIsNotFound() throws Exception {
            final long id = 222L;
            final MockHttpServletRequestBuilder request = get(joinWithSlash(ENDPOINT, String.valueOf(id)));

            mockMvc.perform(request)
                    .andExpect(status().isNotFound())
                    .andExpect(verifyExceptionType("fr.weeab.mangalist.core.exception.NotFoundException"))
                    .andExpect(verifyExceptionTitle("error.server.not-found.manga.title"))
                    .andExpect(verifyExceptionMessage("error.server.not-found.manga.msg"))
                    .andExpect(verifyExceptionCause("error.server.not-found.manga.cause"));
        }
    }

    @Nested
    class SearchManga {

        @Test
        void getMangaByName_ok_shouldReturnAMangaDTO() throws Exception {
            PagerDTO pager = new PagerDTO(0, 9);
            MangaCriteriaDTO criteria = new MangaCriteriaDTO("test_1");

            PagedSearchDTO<MangaCriteriaDTO> searchDTO = TestWebappUtils.createMangaPagedSearchDTO(pager, criteria);

            final MockHttpServletRequestBuilder request = get(joinWithSlash(ENDPOINT, "search"))
                    .param("pager.page", searchDTO.getPager().getPage().toString())
                    .param("pager.pageSize", searchDTO.getPager().getPageSize().toString())
                    .param("criteria.title", searchDTO.getCriteria().getTitle());

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.items[0].title", equalTo("test_1")));
        }

        @Test
        void getMangaByName_ok_shouldReturnThreeMangaDTO() throws Exception {
            PagerDTO pager = new PagerDTO(0, 9);
            MangaCriteriaDTO criteria = new MangaCriteriaDTO("test");

            PagedSearchDTO<MangaCriteriaDTO> searchDTO = TestWebappUtils.createMangaPagedSearchDTO(pager, criteria);

            final MockHttpServletRequestBuilder request = get(joinWithSlash(ENDPOINT, "search"))
                    .param("pager.page", searchDTO.getPager().getPage().toString())
                    .param("pager.pageSize", searchDTO.getPager().getPageSize().toString())
                    .param("criteria.title", searchDTO.getCriteria().getTitle());

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.items[0].title", equalTo("test_1")))
                    .andExpect(jsonPath("$.items[1].title", equalTo("test_2")))
                    .andExpect(jsonPath("$.items[2].title", equalTo("test_3")))
                    .andExpect(jsonPath("$.items", hasSize(3)))
            ;
        }

        @Test
        void getMangaByName_ko_shouldNotReturnAMangaDTO() throws Exception {
            PagerDTO pager = new PagerDTO(0, 9);
            MangaCriteriaDTO criteria = new MangaCriteriaDTO("test_456789");

            PagedSearchDTO<MangaCriteriaDTO> searchDTO = TestWebappUtils.createMangaPagedSearchDTO(pager, criteria);

            final MockHttpServletRequestBuilder request = get(joinWithSlash(ENDPOINT, "search"))
                    .param("pager.page", searchDTO.getPager().getPage().toString())
                    .param("pager.pageSize", searchDTO.getPager().getPageSize().toString())
                    .param("criteria.title", searchDTO.getCriteria().getTitle());

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.items", empty()));
        }
    }

    @Nested
    class CreateManga {

        @Autowired
        MangaRepository mangaRepository;

        @Test
        void insertManga_shouldReturnInsertedManga() throws Exception {
            final GenreSaveDTO genre = new GenreSaveDTO();
            genre.setId(1L);
            genre.setName("test_1");

            final Set<GenreSaveDTO> genres = new HashSet<>();
            genres.add(genre);

            final JSONObject json = new JSONObject(ofEntries(
                    entry("title", "title"),
                    entry("description", "description"),
                    entry("author", "author"),
                    entry("genres", new JSONArray("[{ \"id\": 1, \"name\":\"test_1\" }]"))
            ));

            final Long expectedId = 4L;

            final MockHttpServletRequestBuilder request = post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.toString());

            mockMvc.perform(request)
                    .andExpect(status().isCreated())
                    .andExpect(header().string("Location", "http://localhost" + ENDPOINT + expectedId));

            assertThat(this.mangaRepository.findById(expectedId))
                    .isPresent()
                    .get()
                    .extracting(Manga::getAuthor, Manga::getDescription, Manga::getTitle)
                    .containsExactly("author", "description", "title");

            final MockHttpServletRequestBuilder genreRequest = get("/api/v1/genres")
                    .param("mangaId", expectedId.toString());

            mockMvc.perform(genreRequest)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", Matchers.hasSize(1)))
                    .andExpect(jsonPath("$[0].name", equalTo("test_1")))
                    .andExpect(jsonPath("$[0].id", equalTo(1)))
                    .andExpect(jsonPath("$[0]", not(hasKey("mangas"))))
                    .andExpect(jsonPath("$[0]", not(hasKey("entityVersion"))));
        }
    }

    @Nested
    class DeleteManga {

        @Test
        void deleteManga_shouldReturnAccepted() throws Exception {
            final long id = 2L;

            final MockHttpServletRequestBuilder request = delete(joinWithSlash(ENDPOINT, String.valueOf(id)));
            mockMvc.perform(request).andExpect(status().isNoContent());

            final MockHttpServletRequestBuilder requestManga = get(joinWithSlash(ENDPOINT, String.valueOf(id)));
            mockMvc.perform(requestManga)
                    .andExpect(status().isNotFound())
                    .andExpect(verifyExceptionType("fr.weeab.mangalist.core.exception.NotFoundException"))
                    .andExpect(verifyExceptionTitle("error.server.not-found.manga.title"))
                    .andExpect(verifyExceptionMessage("error.server.not-found.manga.msg"))
                    .andExpect(verifyExceptionCause("error.server.not-found.manga.cause"));
        }

        @Test
        void deleteManga_shouldReturnNotFound() throws Exception {
            final long id = 2456L;

            final MockHttpServletRequestBuilder request = delete(joinWithSlash(ENDPOINT, String.valueOf(id)));

            mockMvc.perform(request)
                    .andExpect(status().isNotFound())
                    .andExpect(verifyExceptionType("fr.weeab.mangalist.core.exception.NotFoundException"))
                    .andExpect(verifyExceptionTitle("error.server.not-found.manga.title"))
                    .andExpect(verifyExceptionMessage("error.server.not-found.manga.msg"))
                    .andExpect(verifyExceptionCause("error.server.not-found.manga.cause"));
        }
    }
}
