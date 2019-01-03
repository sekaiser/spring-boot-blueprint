package de.seka.news.modules.articles.jpa.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.services.ArticlePersistenceService;

import javax.validation.constraints.NotNull;

public class JpaArticlePersistenceServiceImpl extends JpaBaseService implements ArticlePersistenceService {

    private final JpaArticleRepository articleRepository;

    public JpaArticlePersistenceServiceImpl(
            final JpaArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void createArticle(@NotNull Article article) throws MttrbitException {

    }
}
