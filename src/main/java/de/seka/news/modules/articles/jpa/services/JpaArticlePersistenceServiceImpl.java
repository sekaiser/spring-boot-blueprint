package de.seka.news.modules.articles.jpa.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.services.ArticlePersistenceService;

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

    }
}
