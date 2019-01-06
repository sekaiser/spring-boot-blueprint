package de.seka.news.modules.articles.jpa.entities;

import de.seka.news.modules.articles.jpa.entities.projections.BaseProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

/**
 * The base for all top level entities.
 */
@Getter
@Setter
@ToString(callSuper = true, of = {"version", "user", "name"})
@MappedSuperclass
public class BaseEntity extends UniqueIdEntity implements BaseProjection {

    private static final long serialVersionUID = -6911191097259294883L;

    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    @NotBlank(message = "Version is missing and is required.")
    @Size(max = 255, message = "Max length in database is 255 characters")
    private String version;

    @Basic(optional = false)
    @Column(name = "mttrbit_user", nullable = false)
    @NotBlank(message = "User name is missing and is required.")
    @Size(max = 255, message = "Max length in database is 255 characters")
    private String user;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is missing and is required.")
    @Size(max = 255, message = "Max length in database is 255 characters")
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "metadata", columnDefinition = "TEXT DEFAULT NULL")
    private String metadata;

    /**
     * Default constructor.
     */
    BaseEntity() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getMetadata() {
        return Optional.ofNullable(this.metadata);
    }

    /**
     * Set the JSON metadata of this entity.
     *
     * @param metadata The metadata of this
     */
    public void setMetadata(@Nullable final String metadata) {
        this.metadata = metadata;
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
