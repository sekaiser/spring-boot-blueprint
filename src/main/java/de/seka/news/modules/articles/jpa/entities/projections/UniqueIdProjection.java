package de.seka.news.modules.articles.jpa.entities.projections;

/**
 * Projection for getting the Unique Id of a resource.
 */
public interface UniqueIdProjection extends AuditProjection {

    /**
     * Get the unique identifier for this entity.
     *
     * @return The globally unique identifier of this entity
     */
    String getUniqueId();
}