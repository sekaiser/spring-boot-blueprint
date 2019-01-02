package de.seka.news.modules.articles.services.respositories;

import de.seka.news.modules.articles.models.ArticleEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ArticleSpecifications {

    public static Specification<ArticleEntity> findByKeyword(String keyword) {
        return (Root<ArticleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->  cb.like(root.get("keywords"), "%"+keyword+"%");
    }

    public static Specification<ArticleEntity> findByAuthor(String author) {
        return (Root<ArticleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->  cb.like(root.get("authors"), "%"+author+"%");
    }

    public static Specification<ArticleEntity> findByIdentifier(String identifier) {
        return (Root<ArticleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->  cb.equal(root.get("identifier"), identifier);
    }
}
