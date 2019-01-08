package de.seka.news.modules.articles.services;

import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Loading service to insert test data into test database.
 */
@Slf4j
@Service
public final class DataLoaderService {

    private final String pvDataArticlesCsv;

    private final JpaArticleRepository articleRepository;

    /**
     * Constructor.
     *
     * @param articleRepository The article repository
     * @param pvDataArticlesCsv The path to csv file containing test data
     */
    @Autowired
    public DataLoaderService(
            final JpaArticleRepository articleRepository,
            @Value("${pvdata.articles}") final String pvDataArticlesCsv
    ) {
        this.articleRepository = articleRepository;
        this.pvDataArticlesCsv = pvDataArticlesCsv;
    }

    @PostConstruct
    private void loadData() throws IOException {

        ICsvBeanReader beanReader = null;
        try {
            final Resource resource = new ClassPathResource(pvDataArticlesCsv);
            beanReader = new CsvBeanReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8.name()),
                    CsvPreference.STANDARD_PREFERENCE);

            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] inverterProcessors = getArticlesProcessors();

            ArticleEntity article;
            while ((article = beanReader.read(ArticleEntity.class, header, inverterProcessors)) != null) {
                article.setUser("seka");
                article.setName("article");
                article.setVersion("1.0.0");
                articleRepository.save(article);
            }
        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
    }

    private static CellProcessor[] getArticlesProcessors() {
        return new CellProcessor[]{
                new Optional(), // header
                new Optional(), // description
                new Optional(), // text
                new Optional(), // keywords
                new Optional(), // authors
        };
    }
}
