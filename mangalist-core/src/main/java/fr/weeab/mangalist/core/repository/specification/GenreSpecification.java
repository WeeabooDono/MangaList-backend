package fr.weeab.mangalist.core.repository.specification;

import fr.weeab.mangalist.core.domain.Genre;
import fr.weeab.mangalist.core.domain.Genre_;
import fr.weeab.mangalist.core.domain.criteria.GenreCriteriaDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;

public class GenreSpecification {

    public static Specification<Genre> getGenresByName(GenreCriteriaDTO criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Predicate finalPredicate = null;
            if (criteria != null && StringUtils.hasText(criteria.getName())) {
                finalPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get(Genre_.name)), "%" + criteria.getName() + "%");
            }
            return finalPredicate;
        };
    }
}
