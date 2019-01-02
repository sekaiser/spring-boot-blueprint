package de.seka.news.modules.articles.services;

import de.seka.news.modules.articles.models.ArticleEntity;
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
    ArticleService service;

    @Test
    public void findAll_shouldReturnnonEmptyResult() {
        assertThat(service.findAll(), is(not(empty())));
    }

    @Test
    public void findByKeywords_empty() {
        assertThat(service.findByKeywords("tester"), is(empty()));
    }

    @Test
    public void findByKeywords_notEmpty() {
        assertThat(service.findByKeywords("test"), is(not(empty())));
    }

    @Test
    public void findByAuthors_empty() {
        assertThat(service.findByAuthors("Brooks"), is(empty()));
    }

    @Test
    public void findByAuthors_notEmpty() {
        assertThat(service.findByAuthors("King"), is(not(empty())));
    }

    @Test
    public void findByAuthorSpec_notEmpty() {
        List<ArticleEntity> result = service.findByAuthors("King");
        System.out.println(result);
        assertThat(result, is(not(empty())));
    }
}
