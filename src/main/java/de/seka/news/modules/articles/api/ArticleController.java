package de.seka.news.modules.articles.api;

import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.hateos.assemblers.ArticleResourceAssembler;
import de.seka.news.hateos.resources.ArticleResource;
import de.seka.news.modules.articles.services.ArticleSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/articles", "/v1/articles"})
@Slf4j
public class ArticleController {
    private final ArticleSearchService articleService;
    private final ArticleResourceAssembler assembler;

    @Autowired
    public ArticleController(
            final ArticleSearchService articleService,
            final ArticleResourceAssembler assembler
    ) {
        this.articleService = articleService;
        this.assembler = assembler;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ArticleResource getArticle(@PathVariable(name = "id") String uniqueId) throws MttrbitException {
        return assembler.toResource(articleService.getArticle(uniqueId));
    }
}
