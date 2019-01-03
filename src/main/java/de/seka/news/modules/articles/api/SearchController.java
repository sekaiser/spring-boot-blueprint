package de.seka.news.modules.articles.api;

import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.hateos.assemblers.ArticleResourceAssembler;
import de.seka.news.hateos.resources.ArticleResource;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecificationBuilder;
import de.seka.news.modules.articles.services.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/articles", "/v1/articles"})
public class SearchController {

    private final ArticleSearchService articleService;
    private final ArticleResourceAssembler assembler;

    @Autowired
    public SearchController(
            final ArticleSearchService articleService,
            final ArticleResourceAssembler assembler
    ) {
        this.articleService = articleService;
        this.assembler = assembler;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<ArticleResource> fetchAll(
            @RequestParam(name = "authors", required = false) String authors,
            @RequestParam(name = "keywords", required = false) String keywords
    )  throws MttrbitException {
        if (authors == null && keywords == null)
            return articleService.findAll().stream().map(assembler::toResource).collect(Collectors.toList());

        return articleService.findArticles(new ArticleSpecificationBuilder().withAuthors(authors).withKeywords(keywords))
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

    }
}
