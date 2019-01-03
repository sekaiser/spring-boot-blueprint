package de.seka.news.modules.articles.jpa.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * An extendable entity class for tables which have a UniqueId field.
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "uniqueId", callSuper = false)
@ToString(of = "uniqueId", callSuper = true)
public class UniqueIdEntity extends AuditEntity {

    @Basic(optional = false)
    @Column(name = "unique_id", nullable = false, unique = true, updatable = false)
    @NotBlank(message = "A unique identifier is missing and is required.")
    @Size(max = 255, message = "Max length in database is 255 characters")
    private String uniqueId = UUID.randomUUID().toString();

    @Basic(optional = false)
    @Column(name = "requested_id", nullable = false, updatable = false)
    private boolean requestedId;
}
