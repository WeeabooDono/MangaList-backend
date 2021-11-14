package fr.weeab.mangalist.core.service.impl;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;
import fr.weeab.mangalist.core.repository.GenreRepository;
import fr.weeab.mangalist.core.repository.specification.GenreSpecification;
import fr.weeab.mangalist.core.service.GenreService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAllByMangasIdOrderByNameDesc(GenreCriteriaDTO criteria) {
        return this.genreRepository.findAllByMangasIdOrderByNameDesc(criteria.getMangaId());
    }

    @Override
    public List<Genre> findAllSearched(GenreCriteriaDTO criteria) {
        return this.genreRepository.findAll(GenreSpecification.getGenresByName(criteria));
    }
}
