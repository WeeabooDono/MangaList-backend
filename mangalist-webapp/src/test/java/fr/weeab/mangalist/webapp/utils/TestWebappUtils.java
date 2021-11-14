package fr.weeab.mangalist.webapp.utils;

import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import fr.weeab.mangalist.webapp.transform.dto.pagination.PagedSearchDTO;
import fr.weeab.mangalist.webapp.transform.dto.pagination.PagerDTO;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;

public class TestWebappUtils {

    public static PagedSearchDTO<MangaCriteriaDTO> createMangaPagedSearchDTO(PagerDTO pager, MangaCriteriaDTO mangaCriteriaDTO) {
        PagedSearchDTO<MangaCriteriaDTO> pagedSearchDTO = new PagedSearchDTO<MangaCriteriaDTO>();
        pagedSearchDTO.setPager(pager);
        pagedSearchDTO.setCriteria(mangaCriteriaDTO);
        return pagedSearchDTO;
    }


    public static MockHttpServletRequestBuilder getRequestParams(MockHttpServletRequestBuilder request, ParamArgument ...arguments) {
        Arrays.stream(arguments).forEach(paramArgument -> {
            if(paramArgument != null) {
                request.param(paramArgument.getKey(), paramArgument.getValue());
            }
        });
        return request;
    }
}
