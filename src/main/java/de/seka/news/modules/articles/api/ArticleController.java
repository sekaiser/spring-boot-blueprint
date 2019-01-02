package de.seka.news.modules.articles.api;

import de.seka.news.hateos.assemblers.ArticleResourceAssembler;
import de.seka.news.hateos.resources.ArticleResource;
import de.seka.news.modules.articles.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/articles", "/v1/articles"})
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleResourceAssembler assembler;

    @Autowired
    public ArticleController(
            final ArticleService articleService,
            final ArticleResourceAssembler assembler
    ) {
        this.articleService = articleService;
        this.assembler = assembler;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ArticleResource getArticle(@PathVariable(name = "id") String identifier) {
        return articleService.findByIdentifier(identifier)
                .map(DtoConverters::toArticle)
                .map(assembler::toResource)
                .orElse(null);
    }


}
