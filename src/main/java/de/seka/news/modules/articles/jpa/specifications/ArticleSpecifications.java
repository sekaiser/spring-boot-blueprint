package de.seka.news.modules.articles.jpa.specifications;

import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Utility methods for creating article specific specifications.
 */
public final class ArticleSpecifications {

    /**
     * Constructor.
     */
    private ArticleSpecifications() {
    }

    /**
     * Get a specification filtering articles by keyword.
     *
     * @param keyword the keyword to filter for
     * @return The specification
     */
    public static Specification<ArticleEntity> findByKeyword(final String keyword) {
        return (
                Root<ArticleEntity> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb
        ) -> cb.like(root.get("keywords"), "%" + keyword + "%");
    }

    /**
     * Get a specification filtering articles by author.
     *
     * @param author the keyword to filter for
     * @return The specification
     */
    public static Specification<ArticleEntity> findByAuthor(final String author) {
        return (
                Root<ArticleEntity> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb) -> cb.like(root.get("authors"
        ), "%" + author + "%");
    }

    /**
     * Get a specification filtering articles by unique id.
     *
     * @param uniqueId the uuid to filter for
     * @return The specification
     */
    public static Specification<ArticleEntity> findByUniqueId(final String uniqueId) {
        return (
                Root<ArticleEntity> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb
        ) -> cb.equal(root.get("uniqueId"), uniqueId);
    }

}
