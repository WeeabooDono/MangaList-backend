package fr.weeab.mangalist.webapp.transform.dto.pagination;

import fr.weeab.mangalist.core.domain.criteria.CriteriaDTO;

public class PagedSearchDTO<C extends CriteriaDTO> {

    private PagerDTO pager;

    private C criteria;

    public PagedSearchDTO() {
    }

    public PagerDTO getPager() {
        return pager;
    }

    public void setPager(PagerDTO pager) {
        this.pager = pager;
    }

    public C getCriteria() {
        return criteria;
    }

    public void setCriteria(C criteria) {
        this.criteria = criteria;
    }
}
