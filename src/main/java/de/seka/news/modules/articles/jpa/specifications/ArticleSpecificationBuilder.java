package de.seka.news.modules.articles.jpa.specifications;

import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A builder to create article specific specifications.
 */
public class ArticleSpecificationBuilder {
    private String[] bAuthors;
    private String[] bKeywords;
    private String bUniqueId;

    /**
     * Constructor.
     */
    public ArticleSpecificationBuilder() {
    }

    /**
     * Set the bUniqueId for this specification.
     *
     * @param uniqueId The uuid
     * @return The builder
     */
    public ArticleSpecificationBuilder withUniqueId(@Nullable final String uniqueId) {
        this.bUniqueId = uniqueId;
        return this;
    }

    /**
     * Set the bAuthors for this specification.
     *
     * @param authors The list of bAuthors separated by comma
     * @return The builder
     */
    public ArticleSpecificationBuilder withAuthors(@Nullable final String authors) {
        this.bAuthors = authors == null ? new String[]{} : authors.split(",");
        return this;
    }

    /**
     * Set the bKeywords for this specification.
     *
     * @param keywords The list of bKeywords separated by comma
     * @return The builder
     */
    public ArticleSpecificationBuilder withKeywords(@Nullable final String keywords) {
        this.bKeywords = keywords == null ? new String[]{} : keywords.split(",");
        return this;
    }

    /**
     * Build the specification.
     *
     * @return Create the final specification used for querying articles
     */
    public Optional<Specification<ArticleEntity>> build() {
        final List<Specification<ArticleEntity>> specs = new ArrayList<>();

        if (bUniqueId != null) {
            specs.add(ArticleSpecifications.findByUniqueId(bUniqueId));
        }

        if (bAuthors != null) {
            Stream
                    .of(bAuthors)
                    .map(ArticleSpecifications::findByAuthor)
                    .reduce((id, acc) -> acc.and(id)).ifPresent(specs::add);
        }

        if (bKeywords != null) {
            Stream
                    .of(bKeywords)
                    .map(ArticleSpecifications::findByKeyword)
                    .reduce((id, acc) -> acc.and(id)).ifPresent(specs::add);
        }

        return specs.stream().reduce((s, acc) -> acc.and(s));
    }
}
