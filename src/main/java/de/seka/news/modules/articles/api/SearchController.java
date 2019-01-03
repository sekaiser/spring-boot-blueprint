package de.seka.news.modules.articles.api;

import de.seka.news.common.dto.Article;
import de.seka.news.hateos.assemblers.ArticleResourceAssembler;
import de.seka.news.hateos.resources.ArticleResource;
import de.seka.news.modules.articles.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/articles", "/v1/articles"})
public class SearchController {

    private final ArticleService articleService;
    private final ArticleResourceAssembler assembler;

    @Autowired
    public SearchController(
            final ArticleService articleService,
            final ArticleResourceAssembler assembler
    ) {
        this.articleService = articleService;
        this.assembler = assembler;
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<ArticleResource> fetchAll(
            @RequestParam(name = "authors", required = false) String authors,
            @RequestParam(name = "keywords", required = false) String keywords
    ) {
        if (authors == null && keywords == null)
            return Optional.of(articleService.findAll())
                .map(DtoConverters::toArticles)
                .map(this::toArticleResources)
                .orElse(Collections.emptyList());

        return new ArticleService.ArticleSpecificationBuilder()
                .withAuthors(authors)
                .withKeywords(keywords)
                .build()
                .map(articleService::findAll)
                .map(DtoConverters::toArticles)
                .map(this::toArticleResources)
                .orElse(Collections.emptyList());

    }

    private List<ArticleResource> toArticleResources(List<Article> articles) {
        List<ArticleResource> resources = new ArrayList<>(articles.size());
        articles.forEach(a -> resources.add(assembler.toResource(a)));
        return resources;
    }
}
