package de.seka.news.modules.articles.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecificationBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Interface for searching articles.
 */
@Validated
public interface ArticleSearchService {

    /**
     * Get article information for given article id.
     *
     * @param id id of article to look up
     * @return the article
     * @throws MttrbitException if there is an error
     */
    Article getArticle(@NotBlank(message = "No id entered. Unable to get article.") String id) throws MttrbitException;

    /**
     * Get all published articles.
     *
     * @return the articles
     * @throws MttrbitException if there is an error
     */
    List<Article> getPublishedArticles() throws MttrbitException;

    /**
     * Get all unpublished articles.
     *
     * @return the articles
     * @throws MttrbitException if there is an error
     */
    List<Article> getUnpublishedArticles() throws MttrbitException;

    /**
     * Search for articles which match the given filter criteria.
     *
     * @param builder The article specification builder
     * @return the articles
     * @throws MttrbitException if there is an error
     */
    List<Article> findArticles(ArticleSpecificationBuilder builder) throws MttrbitException;

    /**
     * Search for all articles.
     *
     * @return the articles
     */
    List<Article> findAll();
}
