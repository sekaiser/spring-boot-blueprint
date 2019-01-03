package de.seka.news.modules.articles.jpa.entities.projections;

import java.time.Instant;

/**
 * Returns all the base entity attributes.
 */
public interface AuditProjection extends IdProjection {

    /**
     * Get when this entity was created.
     *
     * @return The created timestamp
     */
    Instant getCreated();

    /**
     * Get when this entity was updated.
     *
     * @return The updated timestamp
     */
    Instant getUpdated();
}
