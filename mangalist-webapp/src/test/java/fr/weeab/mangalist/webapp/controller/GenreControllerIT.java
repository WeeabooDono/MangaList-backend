package fr.weeab.mangalist.webapp.controller;

import fr.weeab.mangalist.webapp.context.AbstractIntegrationTest;
import fr.weeab.mangalist.webapp.utils.ParamArgument;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.stream.Stream;

import static fr.weeab.mangalist.webapp.utils.TestWebappUtils.getRequestParams;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:datasets/repository/manga_repository_test_before.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:datasets/repository/manga_repository_test_after.sql"}),
})
public class GenreControllerIT extends AbstractIntegrationTest {

    private static final String ENDPOINT = "/api/v1/genres/";

    public static Stream<Arguments> provideParamsForGetGenresEndPoint() {
        return Stream.of(
                Arguments.of(3, null, null),
                Arguments.of(2, new ParamArgument("mangaId", "1"), null),
                Arguments.of(0, new ParamArgument("mangaId", "456"), null),
                Arguments.of(3, new ParamArgument("azeaze", "456"), null),
                Arguments.of(3, null, new ParamArgument("name", "te")),
                Arguments.of(1, null, new ParamArgument("name", "test_1")),
                Arguments.of(2, new ParamArgument("mangaId", "1"), new ParamArgument("name", "test_1"))
        );
    }

    @DisplayName("Testing getGenres with parameters \"mangaId\" and \"name\"")
    @ParameterizedTest(name = "#{index} - Request /api/v1/genres?{1}&{2} should return {0} results")
    @MethodSource("provideParamsForGetGenresEndPoint")
    void getGenres_shouldRetrieveAllGenres(int expectedSize, ParamArgument mangaId, ParamArgument name) throws Exception {
        final MockHttpServletRequestBuilder request = getRequestParams(get(ENDPOINT), mangaId, name);

        ResultActions resultActions = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(expectedSize)));

        // if size = 0, then the result is []
        if (expectedSize != 0) {
            resultActions
                    .andExpect(jsonPath("$[0]", not(hasKey("mangas"))))
                    .andExpect(jsonPath("$[0]", not(hasKey("entityVersion"))));
        }

        // if size = 1, then we can check values
        if (expectedSize == 1) {
            resultActions
                    .andExpect(jsonPath("$[0].name", equalTo("test_1")));
        }
    }
}