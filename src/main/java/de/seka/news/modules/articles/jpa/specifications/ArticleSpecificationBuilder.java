package de.seka.news.modules.articles.jpa.specifications;

import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ArticleSpecificationBuilder {
    private String[] authors;
    private String[] keywords;
    private String uniqueId;

    public ArticleSpecificationBuilder() {
    }

    public ArticleSpecificationBuilder withUniqueId(@Nullable String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public ArticleSpecificationBuilder withAuthors(@Nullable String authors) {
        this.authors = authors == null ? new String[]{} : authors.split(",");
        return this;
    }

    public ArticleSpecificationBuilder withKeywords(@Nullable String keywords) {
        this.keywords = keywords == null ? new String[]{} : keywords.split(",");
        return this;
    }

    public Optional<Specification<ArticleEntity>> build() {
        List<Specification<ArticleEntity>> specs = new ArrayList<>();

        if (uniqueId != null) {
            specs.add(ArticleSpecifications.findByUniqueId(uniqueId));
        }

        if (authors != null) {
            Stream.of(authors).map(ArticleSpecifications::findByAuthor).reduce((id, acc) -> acc.and(id)).ifPresent(specs::add);
        }

        if (keywords != null) {
            Stream.of(keywords).map(ArticleSpecifications::findByKeyword).reduce((id, acc) -> acc.and(id)).ifPresent(specs::add);
        }

        return specs.stream().reduce((s, acc) -> acc.and(s));
    }
}