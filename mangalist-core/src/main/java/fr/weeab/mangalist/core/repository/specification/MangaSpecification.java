package fr.weeab.mangalist.core.repository.specification;

import fr.weeab.mangalist.core.domain.Manga;
import fr.weeab.mangalist.core.domain.Manga_;
import fr.weeab.mangalist.core.domain.criteria.MangaCriteriaDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;

public class MangaSpecification {

    public static Specification<Manga> getMangasByTitle(MangaCriteriaDTO criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Predicate finalPredicate = null;
            if(criteria != null && StringUtils.hasText(criteria.getTitle())) {
                finalPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(Manga_.title)), "%" + criteria.getTitle() + "%");
            }
            return finalPredicate;
        };
    }
}
