package de.seka.news.modules.articles.jpa.entities.projections;

import java.time.Instant;
import java.util.Optional;

/**
 * Projection to return only the fields desired for an article with search results.
 */
public interface ArticleSearchProjection {
    /**
     * Get the unique identifier of the article.
     *
     * @return The unique identifier
     */
    String getUniqueId();

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
     * Get the authors of the article.
     *
     * @return The authors
     */
    Optional<String> getAuthors();

    /**
     * Get the keywords of teh article.
     *
     * @return The keywords
     */
    Optional<String> getKeywords();

    /**
     * Get the date the article got published.
     *
     * @return The date of publication
     */
    Optional<Instant> getPublished();
}
