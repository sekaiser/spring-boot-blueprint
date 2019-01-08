package de.seka.news.modules.articles.services;

import de.seka.news.common.dto.Article;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.jpa.services.JpaArticleSearchServiceImpl;
import de.seka.news.modules.articles.jpa.specifications.ArticleSpecificationBuilder;
import de.seka.news.test.categories.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Unit tests for ArticleSearchServiceImpl.
 */
@Category(UnitTest.class)
public class JpaArticleSearchServiceImplTest {

    private JpaArticleRepository articleRepository;
    private JpaArticleSearchServiceImpl service;

    /**
     * Setup for the tests.
     */
    @Before
    public void setup() {
        this.articleRepository = Mockito.mock(JpaArticleRepository.class);
        this.service = new JpaArticleSearchServiceImpl(this.articleRepository);

        Mockito.when(this.service.findAll()).thenReturn(Collections.emptyList());
    }

    /**
     * Test the findAll method().
     */
    @Test
    public void shouldReturnEmptyResult() {
        MatcherAssert.assertThat(service.findAll(), Matchers.is(Matchers.empty()));
    }

    /**
     * Test the findArticles method.
     */
    @Test
    public void findByKeywordsEmpty() {
        final ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
            .withAuthors("tester");
        final List<Article> result = service.findArticles(builder);
        MatcherAssert.assertThat(result, Matchers.is(Matchers.empty()));
    }

    /**
     * Test the findArticles method.
     */
    @Test
    public void findByAuthorsEmpty() {
        final ArticleSpecificationBuilder builder = new ArticleSpecificationBuilder()
            .withAuthors("Brooks");
        final List<Article> result = service.findArticles(builder);
        MatcherAssert.assertThat(result, Matchers.is(Matchers.empty()));
    }
}
