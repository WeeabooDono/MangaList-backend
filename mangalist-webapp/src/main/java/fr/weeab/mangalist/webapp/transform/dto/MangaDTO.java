package fr.weeab.mangalist.webapp.transform.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.Manga;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class MangaDTO extends Manga {
    private static final long serialVersionUID = 6349162253551326378L;

    @Override
    @JsonIgnore
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @Override
    @JsonIgnore
    public Timestamp getCreatedDate() {
        return super.getCreatedDate();
    }

    @Override
    @JsonIgnore
    public String getLastModifiedBy() {
        return super.getLastModifiedBy();
    }

    @Override
    @JsonIgnore
    public Timestamp getLastModifiedDate() {
        return super.getLastModifiedDate();
    }

    @Override
    @JsonIgnore
    public Integer getEntityVersion() {
        return super.getEntityVersion();
    }

    @Override
    @JsonIgnore
    public String getImage() {
        return super.getImage();
    }

    @Override
    @JsonIgnoreProperties("mangas")
    public Set<Genre> getGenres() {
        return super.getGenres();
    }
}
