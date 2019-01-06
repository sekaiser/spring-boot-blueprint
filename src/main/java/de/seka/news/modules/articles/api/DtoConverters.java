package de.seka.news.modules.articles.api;

import de.seka.news.common.dto.Article;
import de.seka.news.modules.articles.jpa.entities.ArticleEntity;

import java.util.ArrayList;
import java.util.List;

public interface DtoConverters {

    static Article toArticle(final ArticleEntity entity) {
        final Article.Builder builder = new Article.Builder("seka", "seka", "1.0.0")
                .withId(entity.getUniqueId())
                .withHeader(entity.getHeader())
                .withDescription(entity.getDescription())
                .withText(entity.getText());

        entity.getAuthors().ifPresent(builder::withAuthors);
        entity.getKeywords().ifPresent(builder::withKeywords);

        return builder.build();
    }

    static List<Article> toArticles(final List<ArticleEntity> entities) {
        final List<Article> articles = new ArrayList<>();
        entities.forEach(e -> articles.add(toArticle(e)));
        return articles;
    }
}
