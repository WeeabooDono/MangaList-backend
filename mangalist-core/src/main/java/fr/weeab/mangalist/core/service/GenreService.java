package fr.weeab.mangalist.core.service;

import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;
import fr.weeab.mangalist.core.transform.dto.GenreDTO;
import fr.weeab.mangalist.core.transform.dto.GenreSaveDTO;

import java.util.List;

public interface GenreService {
    List<GenreSaveDTO> findAllByMangasIdOrderByNameDesc(GenreCriteriaDTO criteria);

    List<GenreSaveDTO> findAllSearched(GenreCriteriaDTO criteria);
}
