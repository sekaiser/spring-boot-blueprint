package de.seka.news.hateoas.assemblers;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.hateoas.resources.ArticleResource;
import de.seka.news.modules.articles.api.ArticleController;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Assembles article resources out of article data transfer objects.
 */
@Component
public class ArticleResourceAssembler implements ResourceAssembler<Article, ArticleResource> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleResource toResource(final Article article) {
        final String id = article.getId().orElseThrow(IllegalArgumentException::new);
        final ArticleResource articleResource = new ArticleResource(article);

        try {

            articleResource.add(ControllerLinkBuilder.linkTo(
                    ControllerLinkBuilder.methodOn(ArticleController.class).getArticle(id)
            ).withSelfRel());
        } catch (final MttrbitException me) {
            // If we can't convert it we might as well force a server exception
            throw new RuntimeException(me);
        }
        return articleResource;
    }
}
