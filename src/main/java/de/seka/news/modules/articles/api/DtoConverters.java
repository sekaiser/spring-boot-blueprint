package de.seka.news.modules.articles.api;

import de.seka.news.common.dto.Article;
import de.seka.news.modules.articles.jpa.entities.ArticleEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface DtoConverters {

    static Article toArticle(final ArticleEntity entity) {

        List<String> authors = entity.getAuthors() == null ? Collections.emptyList() : Arrays.asList(entity.getAuthors().split(","));
        List<String> keywords = entity.getKeywords() == null ? Collections.emptyList() : Arrays.asList(entity.getKeywords().split(","));

        return new Article.Builder("seka", "seka", "1.0.0")
                .withId(entity.getIdentifier())
                .withAuthors(authors)
                .withHeader(entity.getHeader())
                .withDescription(entity.getDescription())
                .withKeywords(keywords)
                .withText(entity.getText())
                .build();
    }

    static List<Article> toArticles(final List<ArticleEntity> entities) {
        List<Article> articles = new ArrayList<>();
        entities.forEach(e -> articles.add(toArticle(e)));
        return articles;
    }
}
