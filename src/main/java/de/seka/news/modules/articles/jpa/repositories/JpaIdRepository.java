package de.seka.news.modules.articles.jpa.repositories;

import de.seka.news.modules.articles.jpa.entities.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * A base repository for all JPA repositories to inherit from to save boilerplate.
 *
 * @param <E> The entity type which must extend IdEntity
 */
@NoRepositoryBean
interface JpaIdRepository<E extends IdEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}
