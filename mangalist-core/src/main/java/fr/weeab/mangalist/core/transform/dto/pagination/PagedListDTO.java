package fr.weeab.mangalist.core.transform.dto.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagedListDTO<T> implements Serializable {

    private static final long serialVersionUID = -7298375620832559291L;

    private transient List<T> items = new ArrayList<>();

    private long total;

    private PagerDTO pager;

    public PagedListDTO() {
        super();
        total = 0;
    }

    public PagedListDTO(List<T> items, long total, PagerDTO pager) {
        this();
        this.items = items;
        this.total = total;
        this.pager = pager;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public PagerDTO getPager() {
        return pager;
    }

    public void setPager(PagerDTO pager) {
        this.pager = pager;
    }
}
