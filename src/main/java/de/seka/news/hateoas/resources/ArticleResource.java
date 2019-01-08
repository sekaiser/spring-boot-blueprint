package de.seka.news.hateoas.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.seka.news.common.dto.Article;
import org.springframework.hateoas.Resource;

/**
 * HATEOAS resource representation of an ArticleEntity.
 */
public class ArticleResource extends Resource<Article> {

    /**
     * Constructor.
     *
     * @param article the article
     */
    @JsonCreator
    public ArticleResource(final Article article) {
        super(article);
    }
}
