package fr.weeab.mangalist.core.service;

import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import fr.weeab.mangalist.core.transform.dto.MangaDTO;
import fr.weeab.mangalist.core.transform.dto.MangaSaveDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagedListDTO;
import fr.weeab.mangalist.core.transform.dto.pagination.PagerDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface MangaService {
    PagedListDTO<MangaDTO> findAll(PagerDTO pager);

    PagedListDTO<MangaDTO> findAllSearched(MangaCriteriaDTO criteria, PagerDTO pager);

    MangaDTO findById(Long id);

    MangaSaveDTO save(@Valid MangaDTO dto);

    void deleteById(Long id);
}
