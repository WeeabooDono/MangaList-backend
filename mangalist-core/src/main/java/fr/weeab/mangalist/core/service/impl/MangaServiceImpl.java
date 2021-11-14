package fr.weeab.mangalist.core.service.impl;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import fr.weeab.mangalist.core.exception.NotFoundException;
import fr.weeab.mangalist.core.repository.GenreRepository;
import fr.weeab.mangalist.core.repository.MangaRepository;
import fr.weeab.mangalist.core.repository.specification.MangaSpecification;
import fr.weeab.mangalist.core.service.MangaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.weeab.mangalist.core.exception.NotFoundException.NotFoundExceptionType.MANGA_NOT_FOUND;

@Service
public class MangaServiceImpl implements MangaService {

    private final MangaRepository mangaRepository;
    private final GenreRepository genreRepository;
    private final EntityManager entityManager;

    public MangaServiceImpl(MangaRepository mangaRepository, GenreRepository genreRepository, EntityManager entityManager) {
        this.mangaRepository = mangaRepository;
        this.genreRepository = genreRepository;
        this.entityManager = entityManager;
    }

    public Page<Manga> findAll(Pageable pageable) {
        return this.mangaRepository.findAll(pageable);
    }

    public Page<Manga> findAllSearched(MangaCriteriaDTO criteria, Pageable pageable) {
        return this.mangaRepository.findAll(MangaSpecification.getMangasByTitle(criteria), pageable);
    }

    public Manga findById(Long id) {
        return this.mangaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MANGA_NOT_FOUND));
    }

    public Manga save(Manga manga) {
        // detach genres from entity
        // Get genres by ID in hibernate context to add it and not create it
        Set<Genre> genres = Set.copyOf(this.genreRepository.findAllById(manga.getGenres()
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toSet())));
        manga.setGenres(genres);
        return this.mangaRepository.save(manga);
    }

    public Manga update(Long id, Manga entity) {
        Manga mangaToUpdate = this.mangaRepository.getById(id);
        if (entity.getAuthor() != null) mangaToUpdate.setAuthor(entity.getAuthor());
        if (entity.getDescription() != null) mangaToUpdate.setDescription(entity.getDescription());
        if (entity.getImage() != null) mangaToUpdate.setImage(entity.getImage());
        if (entity.getTitle() != null) mangaToUpdate.setTitle(entity.getTitle());

        return this.mangaRepository.save(mangaToUpdate);
    }

    public void deleteById(Long id) {
        if (!this.mangaRepository.existsById(id)) {
            throw new NotFoundException(MANGA_NOT_FOUND);
        }

        this.mangaRepository.deleteMangaById(id);
    }
}
