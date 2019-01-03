package de.seka.news.modules.articles.jpa.entities.projections;

/**
 * A projection just for returning the id field of a given entity.
 */
public interface IdProjection {

    /**
     * Get the id from the projection.
     *
     * @return The id
     */
    long getId();
}
