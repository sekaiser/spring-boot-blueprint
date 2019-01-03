package de.seka.news.modules.articles.jpa.entities;

import de.seka.news.modules.articles.jpa.entities.projections.AuditProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

/**
 * Abstract class to support basic columns for all entities for this example.
 *
 * @author tgianos
 */
@Getter
@ToString(callSuper = true, of = {"created", "updated"})
@MappedSuperclass
public class AuditEntity extends IdEntity implements AuditProjection {

    @Basic(optional = false)
    @Column(name = "created", nullable = false, updatable = false)
    private Instant created = Instant.now();

    @Basic(optional = false)
    @Column(name = "updated", nullable = false)
    private Instant updated = Instant.now();

    /**
     * Updates the created and updated timestamps to be creation time.
     */
    @PrePersist
    protected void onCreateBaseEntity() {
        final Instant now = Instant.now();
        this.updated = now;
        this.created = now;
    }

    /**
     * On any update to the entity will update the update time.
     */
    @PreUpdate
    protected void onUpdateBaseEntity() {
        this.updated = Instant.now();
    }

    /**
     * Get when this entity was created.
     *
     * @return The created timestamps
     */
    public Instant getCreated() {
        return this.created;
    }

    /**
     * Get the time this entity was updated.
     *
     * @return The updated timestamp
     */
    public Instant getUpdated() {
        return this.updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
