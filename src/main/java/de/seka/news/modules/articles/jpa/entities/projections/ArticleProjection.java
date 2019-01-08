package de.seka.news.modules.articles.jpa.entities.projections;

import java.time.Instant;
import java.util.Optional;

/**
 * Projection to return all the fields of an article.
 */
public interface ArticleProjection extends BaseProjection {
    /**
     * Get the header of the article.
     *
     * @return The header
     */
    String getHeader();

    /**
     * Get the description of the article.
     *
     * @return The description
     */
    String getDescription();

    /**
     * Get the text of the article.
     *
     * @return The content
     */
    String getText();

    /**
     * Get the keywords of teh article.
     *
     * @return The keywords
     */
    Optional<String> getKeywords();

    /**
     * Get the authors of the article.
     *
     * @return The authors
     */
    Optional<String> getAuthors();

    /**
     * Get the date the article got published.
     *
     * @return The date of publication
     */
    Optional<Instant> getPublished();
}
