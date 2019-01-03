package de.seka.news.modules.articles.jpa.services;

import de.seka.news.modules.articles.jpa.entities.UniqueIdEntity;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Base service for other services to extend for common functionality.
 */
@Slf4j
@Getter(AccessLevel.PACKAGE)
class JpaBaseService {

    JpaBaseService() { }

    /**
     * Set the unique id and other related fields for an entity.
     *
     * @param entity      The entity to set the unique id for
     * @param requestedId The id requested if there was one. Null if not.
     * @param <E>         The entity type which must extend {@link UniqueIdEntity}
     */
    <E extends UniqueIdEntity> void setUniqueId(final E entity, @Nullable final String requestedId) {
        if (requestedId != null) {
            entity.setUniqueId(requestedId);
            entity.setRequestedId(true);
        } else {
            entity.setUniqueId(UUID.randomUUID().toString());
            entity.setRequestedId(false);
        }
    }
}
