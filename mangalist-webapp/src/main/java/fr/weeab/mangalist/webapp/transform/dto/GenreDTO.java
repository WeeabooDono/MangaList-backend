package fr.weeab.mangalist.webapp.transform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.Manga;

import java.util.Set;

public class GenreDTO extends Genre {
    private static final long serialVersionUID = 5279369983152232946L;

    @Override
    @JsonIgnore
    public Integer getEntityVersion() {
        return super.getEntityVersion();
    }

    @Override
    @JsonIgnore
    public Set<Manga> getMangas() {
        return super.getMangas();
    }
}
