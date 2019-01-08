package de.seka.news.modules.articles.api;

import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.hateoas.assemblers.ArticleResourceAssembler;
import de.seka.news.hateoas.resources.ArticleResource;
import de.seka.news.modules.articles.services.ArticleSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint for articles.
 */
@RestController
@RequestMapping({"/articles", "/v1/articles"})
@Slf4j
public class ArticleController {
    private final ArticleSearchService articleService;
    private final ArticleResourceAssembler assembler;

    /**
     * Constructor.
     *
     * @param articleService The article service to use
     * @param assembler      Assemble article resources out of articles
     */
    @Autowired
    public ArticleController(
            final ArticleSearchService articleService,
            final ArticleResourceAssembler assembler
    ) {
        this.articleService = articleService;
        this.assembler = assembler;
    }

    /**
     * Fetch the article to an id.
     *
     * @param uniqueId The uuid of the article
     * @return the article
     * @throws MttrbitException if there was an error
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ArticleResource getArticle(@PathVariable(name = "id") final String uniqueId) throws MttrbitException {
        return assembler.toResource(articleService.getArticle(uniqueId));
    }
}
