package de.seka.news.modules.articles.jpa.services;

import de.seka.news.common.dto.Article;
import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility methods for JPA services.
 */
@Slf4j
public final class JpaServiceUtils {

    private JpaServiceUtils() {
    }

    static Article toArticleDto(final ArticleEntity articleEntity) {
        final Article.Builder builder = new Article.Builder(
                articleEntity.getName(),
                articleEntity.getUser(),
                articleEntity.getVersion()
        )
                .withHeader(articleEntity.getHeader())
                .withDescription(articleEntity.getDescription())
                .withText(articleEntity.getText())
                .withId(articleEntity.getUniqueId())
                .withCreated(articleEntity.getCreated())
                .withUpdated(articleEntity.getUpdated());

        articleEntity.getAuthors().ifPresent(builder::withAuthors);
        articleEntity.getKeywords().ifPresent(builder::withKeywords);

        return builder.build();
    }
}
