package de.seka.news.modules.articles.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Interface for providing persistence functions for articles other than search.
 */
@Validated
public interface ArticlePersistenceService {

    /**
     * Save teh initial article fields in the data store.
     *
     * @param article the article to persist
     * @throws MttrbitException if there is an error
     */
    void createArticle(@NotNull Article article) throws MttrbitException;
}
