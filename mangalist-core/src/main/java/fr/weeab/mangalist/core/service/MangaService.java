package fr.weeab.mangalist.core.service;

import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MangaService {
    Page<Manga> findAll(Pageable pageable);

    Page<Manga> findAllSearched(MangaCriteriaDTO criteria, Pageable pageable);

    Manga findById(Long id);

    Manga save(Manga manga);

    void deleteById(Long id);
}
