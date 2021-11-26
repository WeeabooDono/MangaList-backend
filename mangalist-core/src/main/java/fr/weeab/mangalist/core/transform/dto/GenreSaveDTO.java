package fr.weeab.mangalist.core.transform.dto;

import javax.validation.constraints.NotNull;

public class GenreSaveDTO extends GenreDTO {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
