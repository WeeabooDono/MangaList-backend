package fr.weeab.mangalist.core.transform.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class MangaDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String author;

    @NotNull
    @Size(min = 1)
    private Set<@Valid GenreSaveDTO> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<GenreSaveDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreSaveDTO> genres) {
        this.genres = genres;
    }
}
