package fr.weeab.mangalist.core.repository;

import fr.weeab.mangalist.core.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {
    List<Genre> findAllByOrderByNameDesc();

    @Query("SELECT g FROM Genre g JOIN g.mangas m WHERE m.id = ?1")
    List<Genre> findAllByMangasIdOrderByNameDesc(Long mangaId);
}
