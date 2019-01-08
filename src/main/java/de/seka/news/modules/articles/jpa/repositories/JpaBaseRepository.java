package de.seka.news.modules.articles.jpa.repositories;

import de.seka.news.modules.articles.jpa.entities.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * A common repository for inheritance of common methods for Entities extending BaseEntity.
 *
 * @param <E> The entity class to act on which must extend BaseEntity
 */
@NoRepositoryBean
public interface JpaBaseRepository<E extends BaseEntity> extends JpaIdRepository<E> {

    /**
     * Find an entity by its unique id.
     *
     * @param uniqueId The unique id to find an entity for
     * @return The entity found or empty Optional
     */
    Optional<E> findByUniqueId(String uniqueId);

    // TODO: Make interfaces generic but be aware of https://jira.spring.io/browse/DATAJPA-1185

    /**
     * Find an entity by its unique id.
     *
     * @param <T>      The class type which is a projection of E
     * @param uniqueId The unique id to find an entity for
     * @param type     The entity or projection type to return
     * @return The entity found or empty Optional
     */
    <T> Optional<T> findByUniqueId(String uniqueId, Class<T> type);

    /**
     * Find out whether an entity with the given unique id exists.
     *
     * @param uniqueId The unique id to check for existence
     * @return True if an entity with the unique id exists
     */
    boolean existsByUniqueId(String uniqueId);
}
