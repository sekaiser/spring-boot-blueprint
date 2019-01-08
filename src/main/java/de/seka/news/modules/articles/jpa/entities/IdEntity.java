package de.seka.news.modules.articles.jpa.entities;

import de.seka.news.modules.articles.jpa.entities.projections.IdProjection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base class which only provides an ID. For use cases when we don't care about audit fields like
 * created, updated, entity version etc.
 */
@Getter
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
@MappedSuperclass
public class IdEntity implements IdProjection, Serializable {

    private static final long serialVersionUID = 6103892494923329226L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
}
