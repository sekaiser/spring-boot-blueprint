package de.seka.news.modules.articles.jpa.entities;

import de.seka.news.modules.articles.jpa.entities.projections.ArticleProjection;
import de.seka.news.modules.articles.jpa.entities.projections.ArticleSearchProjection;
import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Entity
@ToString(callSuper = true, of = {"header", "description", "text", "keywords", "authors", "published"})
@Table(name = "articles")
@Getter
@Setter
public class ArticleEntity extends BaseEntity implements ArticleProjection, ArticleSearchProjection {

    private static final long serialVersionUID = 1566996076779823541L;

    @Basic(optional = false)
    @Column
    private String header;
    @Basic(optional = false)
    @Column(columnDefinition = "TEXT")
    private String description;
    @Basic(optional = false)
    @Column(columnDefinition = "TEXT")
    private String text;
    @Basic
    @Column(name = "published")
    private Instant published;

    // assumption: keywords are separated by spaces
    @Basic
    @Column
    private String keywords;

    // assumption author consists of firstname lastname; authors are seprated by ','
    @Basic
    @Column
    private String authors;

    public ArticleEntity() {
        super();
    }

    public Optional<String> getAuthors() {
        return Optional.ofNullable(this.authors);
    }

    public Optional<String> getKeywords() {
        return Optional.ofNullable(this.keywords);
    }

    public Optional<Instant> getPublished() {
        return Optional.ofNullable(this.published);
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