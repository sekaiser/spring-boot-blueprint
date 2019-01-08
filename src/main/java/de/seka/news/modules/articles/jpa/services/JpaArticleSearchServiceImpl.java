package de.seka.news.modules.articles.jpa.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.common.exceptions.MttrbitNotFoundException;
import de.seka.news.modules.articles.jpa.entities.projections.ArticleProjection;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecificationBuilder;
import de.seka.news.modules.articles.services.ArticleSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

/**
 * JPA implementation of the Article Search Service.
 */
@Transactional(readOnly = true)
@Slf4j
public class JpaArticleSearchServiceImpl implements ArticleSearchService {

    private final JpaArticleRepository articleRepository;

    /**
     * Constructor.
     *
     * @param articleRepository The article repository.
     */
    public JpaArticleSearchServiceImpl(final JpaArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article getArticle(
            @NotBlank(message = "No id entered. Unable to get article.") final String id
    ) throws MttrbitException {
        return JpaServiceUtils.toArticleDto(
                articleRepository
                        .findByUniqueId(id, ArticleProjection.class)
                        .orElseThrow(() -> new MttrbitNotFoundException("No article with id " + id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getPublishedArticles() throws MttrbitException {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getUnpublishedArticles() throws MttrbitException {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> findArticles(final ArticleSpecificationBuilder builder) {
        return builder.build()
                .map(spec -> articleRepository
                        .findAll(Specification.where(spec))
                        .stream()
                        .map(JpaServiceUtils::toArticleDto)
                        .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll().stream().map(JpaServiceUtils::toArticleDto).collect(Collectors.toList());
    }
}
