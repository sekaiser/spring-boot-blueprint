package de.seka.news.modules.articles.jpa.entities.projections;

import java.util.Optional;

/**
 * A projection just for returning the base fields of a given entity.
 */
public interface BaseProjection extends UniqueIdProjection {

    /**
     * Get the version.
     *
     * @return The version of the resource
     */
    String getVersion();

    /**
     * Get the user who created the resource.
     *
     * @return The user who created the resource
     */
    String getUser();

    /**
     * Get the name of the resource.
     *
     * @return The name of the resource
     */
    String getName();

    /**
     * Get the metadata of this entity which is unstructured JSON.
     *
     * @return Optional of the metadata json node represented as a string
     */
    Optional<String> getMetadata();
}
