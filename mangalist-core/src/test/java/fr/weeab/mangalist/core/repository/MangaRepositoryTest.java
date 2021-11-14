package fr.weeab.mangalist.core.repository;

import fr.weeab.mangalist.core.context.AbstractRepositoryTest;
import fr.weeab.mangalist.core.domain.Manga;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
         scripts = { "classpath:datasets/repository/manga_repository_test_before.sql" }),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
         scripts = { "classpath:datasets/repository/manga_repository_test_after.sql" }),
})
public class MangaRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private MangaRepository mangaRepository;

    @Test
    // useless test since it is already tested by JPA
    void findAll_shouldReturnAllMangas() {
        final int size = 3;
        List<Manga> mangas = mangaRepository.findAll();

        assertThat(mangas).hasSize(size);
    }
}
