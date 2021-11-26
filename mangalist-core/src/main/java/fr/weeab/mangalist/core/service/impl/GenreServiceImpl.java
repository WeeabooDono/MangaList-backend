package fr.weeab.mangalist.core.service.impl;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;
import fr.weeab.mangalist.core.repository.GenreRepository;
import fr.weeab.mangalist.core.repository.specification.GenreSpecification;
import fr.weeab.mangalist.core.service.GenreService;
import fr.weeab.mangalist.core.transform.dto.GenreDTO;
import fr.weeab.mangalist.core.transform.dto.GenreSaveDTO;
import fr.weeab.mangalist.core.transform.mapper.GenreMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreSaveDTO> findAllByMangasIdOrderByNameDesc(GenreCriteriaDTO criteria) {
        return this.genreMapper.toDTOs(this.genreRepository.findAllByMangasIdOrderByNameDesc(criteria.getMangaId()));
    }

    @Override
    public List<GenreSaveDTO> findAllSearched(GenreCriteriaDTO criteria) {
        return this.genreMapper.toDTOs(this.genreRepository.findAll(GenreSpecification.getGenresByName(criteria)));
    }
}
