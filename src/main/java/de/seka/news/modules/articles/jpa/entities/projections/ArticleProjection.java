package de.seka.news.modules.articles.jpa.entities.projections;

import java.time.Instant;
import java.util.Optional;

public interface ArticleProjection extends BaseProjection {
    String getHeader();
    String getDescription();
    String getText();

    Optional<String> getKeywords();
    Optional<String> getAuthors();

    /**
     * Get when the article was published.
     *
     * @return The start date
     */
    Optional<Instant> getPublished();
}
