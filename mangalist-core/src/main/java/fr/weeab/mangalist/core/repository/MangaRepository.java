package fr.weeab.mangalist.core.repository;

import fr.weeab.mangalist.core.domain.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long>, JpaSpecificationExecutor<Manga> {

    /**
     * Pour la suppression en many to many, on laisse le sgbd gérer la suppression en cascade.
     *
     * @param id du manga
     */
    @Modifying
    @Query("DELETE FROM Manga m WHERE m.id = ?1")
    void deleteMangaById(Long id);
}
