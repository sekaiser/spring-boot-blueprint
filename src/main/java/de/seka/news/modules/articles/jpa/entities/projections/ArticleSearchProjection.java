package de.seka.news.modules.articles.jpa.entities.projections;

import java.time.Instant;
import java.util.Optional;

/**
 * Projection to return only the fields desired for an article with search results.
 */
public interface ArticleSearchProjection {
    /**
     * Get the unique identifier of the job.
     *
     * @return The unique identifier
     */
    String getUniqueId();

    String getHeader();

    String getDescription();

    Optional<String> getAuthors();

    Optional<String> getKeywords();

    Optional<Instant> getPublished();
}
