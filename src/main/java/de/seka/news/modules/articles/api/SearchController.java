package de.seka.news.modules.articles.api;

import de.seka.news.modules.articles.models.Article;
import de.seka.news.modules.articles.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/articles", "/v1/articles"})
public class SearchController {

    private final ArticleService articleService;

    @Autowired
    public SearchController(final ArticleService articleService) {
        this.articleService = articleService;
    }


    // "Search articles by keywords")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Article> fetchAll(
            @RequestParam(name = "authors", required = false) String authors,
            @RequestParam(name = "keywords", required = false) String keywords
    ) {
        return (keywords != null) ?
                this.articleService.findByKeywords(keywords)
                :
                articleService.findAll();
    }
}
