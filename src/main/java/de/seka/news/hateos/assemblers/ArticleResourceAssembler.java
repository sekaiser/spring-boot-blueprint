package de.seka.news.hateos.assemblers;

import de.seka.news.common.dto.Article;
import de.seka.news.hateos.resources.ArticleResource;
import de.seka.news.modules.articles.api.ArticleController;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ArticleResourceAssembler implements ResourceAssembler<Article, ArticleResource> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleResource toResource(final Article article) {
        final String id = article.getId().orElseThrow(IllegalArgumentException::new);
        final ArticleResource articleResource = new ArticleResource(article);

        articleResource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(ArticleController.class).getArticle(id)
        ).withSelfRel());

        return articleResource;
    }
}