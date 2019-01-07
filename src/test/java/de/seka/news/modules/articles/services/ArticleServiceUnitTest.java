package de.seka.news.modules.articles.services;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecificationBuilder;
import de.seka.news.test.categories.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Category(UnitTest.class)
public class ArticleServiceUnitTest {

    private ArticleSearchService service;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.service = Mockito.mock(ArticleSearchService.class);

        Mockito.when(this.service.findArticles(Mockito.any())).thenReturn(Collections.emptyList());
    }

    @Test
    public void findAll_shouldReturnnonEmptyResult() {
        assertThat(service.findAll(), is(not(empty())));
    }

    @Test
    public void findByKeywords_empty() throws MttrbitException {
        ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withAuthors("tester");
        List<Article> result = service.findArticles(builder);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByKeywords_notEmpty() throws MttrbitException {
        ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withKeywords("test");
        List<Article> result = service.findArticles(builder);
        assertThat(result, is(not(empty())));
    }

    @Test
    public void findByAuthors_empty() throws MttrbitException {
        ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withAuthors("Brooks");
        List<Article> result = service.findArticles(builder);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByAuthorSpec_notEmpty() throws MttrbitException {
        ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
                .withAuthors("King");
        List<Article> result = service.findArticles(builder);
        assertThat(result, is(not(empty())));
    }
}
