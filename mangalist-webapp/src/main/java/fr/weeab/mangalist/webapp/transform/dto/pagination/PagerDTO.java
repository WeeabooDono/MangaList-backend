package fr.weeab.mangalist.webapp.transform.dto.pagination;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class PagerDTO implements Serializable {

    private static final long serialVersionUID = -5028568162225889804L;

    private Integer page = 0;

    private Integer pageSize = 0;

    private String sortField;

    private String sortOrder;

    public PagerDTO() {
    }

    public PagerDTO(Integer page, Integer pageSize, String sortField, String sortOrder) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public PagerDTO(Integer page, Integer pageSize) {
        this(page, pageSize, null, null);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public PageRequest toPageRequest() {
        Sort sort = null;
        PageRequest pageRequest = null;
        if (StringUtils.isNotEmpty(sortField)) {
            Sort.Direction direction = Sort.Direction.ASC;
            if (StringUtils.isNotEmpty(sortOrder)) {
                direction = Sort.Direction.valueOf(StringUtils.upperCase(sortOrder));
            }
            sort = Sort.by(direction, sortField);
            pageRequest = PageRequest.of(page, pageSize, sort);
        } else {
            pageRequest = PageRequest.of(page, pageSize);
        }
        return pageRequest;
    }
}
