package fr.weeab.mangalist.core.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre extends AbstractEntity<Long> {

    private static final long serialVersionUID = -719688135352267447L;

    @Column
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Manga> mangas;

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
