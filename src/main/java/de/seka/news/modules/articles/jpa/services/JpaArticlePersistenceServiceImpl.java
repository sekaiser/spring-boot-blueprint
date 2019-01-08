package de.seka.news.modules.articles.jpa.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitConflictException;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.common.exceptions.MttrbitPreconditionException;
import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.services.ArticlePersistenceService;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.constraints.NotNull;

/**
 * JPA implementation of the Article Persistence Service.
 */
public class JpaArticlePersistenceServiceImpl extends JpaBaseService implements ArticlePersistenceService {

    private final JpaArticleRepository articleRepository;

    /**
     * Constructor.
     *
     * @param articleRepository The article repository
     */
    public JpaArticlePersistenceServiceImpl(
            final JpaArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createArticle(@NotNull final Article article) throws MttrbitException {
        final String articleId = article
                .getId()
                .orElseThrow(() -> new MttrbitPreconditionException("No article id entered"));
        final ArticleEntity articleEntity = this.toEntity(articleId, article);
        try {
            this.articleRepository.save(articleEntity);
        } catch (final DataIntegrityViolationException e) {
            throw new MttrbitConflictException("An article with id " + articleId + " already exists", e);
        }
    }

    private ArticleEntity toEntity(final String id, final Article article) {
        final ArticleEntity entity = new ArticleEntity();
        entity.setUniqueId(id);
        entity.setName(article.getName());
        entity.setUser(article.getUser());
        entity.setVersion(article.getVersion());
        entity.setDescription(article.getDescription());
        entity.setHeader(article.getHeader());
        entity.setText(article.getText());

        article.getAuthors().ifPresent(entity::setAuthors);
        article.getKeywords().ifPresent(entity::setKeywords);

        return entity;
    }
}
