package fr.weeab.mangalist.core.service;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;

import java.util.List;

public interface GenreService {
    List<Genre> findAllByMangasIdOrderByNameDesc(GenreCriteriaDTO criteria);

    List<Genre> findAllSearched(GenreCriteriaDTO criteria);
}
