package de.seka.news.modules.articles.services;

import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
public class ArticleService {

    private final JpaArticleRepository jpaArticleRepository;

    @Autowired
    public ArticleService(final JpaArticleRepository jpaArticleRepository) {
        this.jpaArticleRepository = jpaArticleRepository;
    }

    public List<ArticleEntity> findAll() {
        return this.jpaArticleRepository.findAll();
    }

    public List<ArticleEntity> findAll(Specification<ArticleEntity> specification) {
        return jpaArticleRepository.findAll(where(specification));
    }

    public List<ArticleEntity> findByKeywords(String keywords) {
        return findByKeywords(keywords.split(",")).map(e -> this.jpaArticleRepository.findAll(where(e))).orElse(Collections.emptyList());
    }

    public List<ArticleEntity> findByAuthors(String authors) {
        return findByAuthors(authors.split(",")).map(e -> this.jpaArticleRepository.findAll(where(e))).orElse(Collections.emptyList());
    }

    public Optional<ArticleEntity> findByIdentifier(String identifier) {
        return this.jpaArticleRepository.findOne(where(ArticleSpecifications.findByIdentifier(identifier)));
    }

    public ArticleEntity save(ArticleEntity article) {
        return this.jpaArticleRepository.save(article);
    }

    public Optional<Specification<ArticleEntity>> findByAuthors(String[] authors) {
        return Stream.of(authors).map(ArticleSpecifications::findByAuthor).reduce((id, acc) -> acc.and(id));
    }

    public Optional<Specification<ArticleEntity>> findByKeywords(String[] keywords) {
        return Stream.of(keywords).map(ArticleSpecifications::findByKeyword).reduce((id, acc) -> acc.and(id));
    }

    public static class ArticleSpecificationBuilder {
        private String[] authors;
        private String[] keywords;

        public ArticleSpecificationBuilder() {
        }

        public ArticleSpecificationBuilder withAuthors(String authors) {
            this.authors = authors == null ? new String[]{} : authors.split(",");
            return this;
        }

        public ArticleSpecificationBuilder withKeywords(String keywords) {
            this.keywords = keywords == null ? new String[]{} : keywords.split(",");
            return this;
        }

        public Optional<Specification<ArticleEntity>> build() {
            List<Specification<ArticleEntity>> specs = new ArrayList<>();
            if (authors != null) {
                Stream.of(authors).map(ArticleSpecifications::findByAuthor).reduce((id, acc) -> acc.and(id)).ifPresent(specs::add);
            }

            if (keywords != null) {
                Stream.of(keywords).map(ArticleSpecifications::findByKeyword).reduce((id, acc) -> acc.and(id)).ifPresent(specs::add);
            }

            return specs.stream().reduce((s, acc) -> acc.and(s));
        }
    }
}
