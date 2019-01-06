package de.seka.news.modules.articles.jpa.services;

import de.seka.news.common.dto.Article;
import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import de.seka.news.modules.articles.jpa.entities.projections.ArticleProjection;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility methods for JPA services.
 */
@Slf4j
final class JpaServiceUtils {

    private JpaServiceUtils() {
    }

    static Article toArticleDto(final ArticleProjection articleProjection) {
        final Article.Builder builder = new Article.Builder(
                articleProjection.getName(),
                articleProjection.getUser(),
                articleProjection.getVersion()
        )
                .withHeader(articleProjection.getHeader())
                .withDescription(articleProjection.getDescription())
                .withText(articleProjection.getText())
                .withId(articleProjection.getUniqueId())
                .withCreated(articleProjection.getCreated())
                .withUpdated(articleProjection.getUpdated());

        articleProjection.getAuthors().ifPresent(builder::withAuthors);
        articleProjection.getKeywords().ifPresent(builder::withKeywords);

        return builder.build();
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
