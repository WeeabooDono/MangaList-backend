package fr.weeab.mangalist.core.domain;

import com.google.common.base.MoreObjects;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -6500754697142011114L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private T id;

    @Version
    @Column(name = "entity_version")
    private Integer entityVersion;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public Integer getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(Integer entityVersion) {
        this.entityVersion = entityVersion;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("entityVersion", entityVersion)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
