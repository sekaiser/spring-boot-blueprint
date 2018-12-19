package de.seka.news.modules.articles.api;

import de.seka.news.modules.articles.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/articles", "/v1/articles"})
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }


}
