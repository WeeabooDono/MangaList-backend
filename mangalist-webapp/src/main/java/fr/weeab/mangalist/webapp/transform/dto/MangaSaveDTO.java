package fr.weeab.mangalist.webapp.transform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MangaSaveDTO extends MangaDTO {

    private static final long serialVersionUID = -6821470761014310295L;

    @Override
    @JsonIgnore
    public Long getId() {
        return super.getId();
    }
}
