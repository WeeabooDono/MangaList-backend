package fr.weeab.mangalist.core.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "mangas")
public class Manga extends AbstractAuditingEntity<Long>  {

    private static final long serialVersionUID = -6107265379902913942L;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String author;

    @Column
    private String image;

    @ManyToMany
    @JoinTable(
            name="genre_manga",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("description", description)
                .add("author", author)
                .add("image", image)
                .toString();
    }
}

