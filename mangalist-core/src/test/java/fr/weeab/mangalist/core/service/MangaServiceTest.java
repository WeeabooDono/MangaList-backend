package fr.weeab.mangalist.core.service;

import fr.weeab.mangalist.core.context.AbstractMockitoTest;
import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.repository.MangaRepository;
import fr.weeab.mangalist.core.service.impl.MangaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MangaServiceTest extends AbstractMockitoTest {

    @Mock
    private MangaRepository mangaRepository;

    @InjectMocks
    private MangaServiceImpl mangaService;

    @Test
    void getMangas_whenCalledFindAllOnlyOneTime() {
        final int times = 1;
        mangaService.findAll(Pageable.unpaged());

        verify(mangaRepository, times(times)).findAll(Pageable.unpaged());
    }

    @Test
    void getManga_whenCalledFindByIdOnlyOneTime() {
        final int times = 1;
        final long id = 2L;
        mangaService.findById(id);

        verify(mangaRepository, times(times)).findById(id);
    }

    @Test
    void deleteManga_whenCalledDeleteByIdOnlyOneTime() {
        final int times = 1;
        final long id = 1L;
        mangaService.deleteById(id);

        verify(mangaRepository, times(times)).deleteById(id);
    }

    @Test
    void insertManga_whenCalledSaveOnlyOneTime() {
        final int times = 1;
        final Manga manga = new Manga();
        mangaService.save(manga);

        verify(mangaRepository, times(times)).save(manga);
    }

    @Test
    void updateManga_whenCalledGetByIdAndSaveOnlyOneTime() {
        final int times = 1;
        final long id = 1L;

        final Manga manga = new Manga();
        manga.setId(id);
        manga.setTitle("title");
        manga.setAuthor("author");
        manga.setDescription("description");
        manga.setImage("image");

        when(mangaRepository.getById(id)).thenReturn(manga);
        when(mangaRepository.save(manga)).thenReturn(manga);

        Manga result = mangaService.update(id, manga);

        assertThat(result).isSameAs(manga);
        verify(mangaRepository, times(times)).getById(id);
        verify(mangaRepository, times(times)).save(manga);
    }
}
