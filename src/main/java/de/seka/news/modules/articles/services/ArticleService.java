package de.seka.news.modules.articles.services;

import de.seka.news.modules.articles.models.Article;
import de.seka.news.modules.articles.services.respositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    public List<Article> findByKeywords(String keywords) {
        return this.articleRepository.findByKeywords(keywords);
    }

    public Optional<Article> findByArticleId(Long id) {
        return this.articleRepository.findById(id);
    }

    public Article save(Article article) {
        return this.articleRepository.save(article);
    }
}
