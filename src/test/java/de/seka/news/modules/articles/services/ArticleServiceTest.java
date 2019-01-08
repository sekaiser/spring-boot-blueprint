package de.seka.news.modules.articles.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecificationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    ArticleSearchService service;

    @Test
    public void findAllShouldReturnnonEmptyResult() {
        assertThat(service.findAll(), is(not(empty())));
    }

    @Test
    public void findByKeywords_empty() throws MttrbitException {
        final ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withAuthors("tester");
        final List<Article> result = service.findArticles(builder);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByKeywords_notEmpty() throws MttrbitException {
        final ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withKeywords("test");
        final List<Article> result = service.findArticles(builder);
        assertThat(result, is(not(empty())));
    }

    @Test
    public void findByAuthors_empty() throws MttrbitException {
        final ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withAuthors("Brooks");
        final List<Article> result = service.findArticles(builder);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByAuthorSpec_notEmpty() throws MttrbitException {
        final ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withAuthors("King");
        final List<Article> result = service.findArticles(builder);
        assertThat(result, is(not(empty())));
    }
}
