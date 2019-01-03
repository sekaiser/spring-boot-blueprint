package de.seka.news.modules.articles.services;

import de.seka.news.modules.articles.jpa.entities.ArticleEntity;
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

@Slf4j
@Service
public class DataLoaderService {

    private final String pvDataArticlesCsv;

    private final ArticleService articleService;

    @Autowired
    public DataLoaderService(
            final ArticleService articleService,
            @Value("${pvdata.articles}") final String pvDataArticlesCsv
    ) {
        this.articleService = articleService;
        this.pvDataArticlesCsv = pvDataArticlesCsv;
    }

    @PostConstruct
    private void loadData() throws IOException {

        ICsvBeanReader beanReader = null;
        try {
            Resource resource = new ClassPathResource(pvDataArticlesCsv);
            beanReader = new CsvBeanReader(
                    new InputStreamReader(resource.getInputStream()),
                    CsvPreference.STANDARD_PREFERENCE);

            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] inverterProcessors = getArticlesProcessors();

            ArticleEntity article;
            while ((article = beanReader.read(ArticleEntity.class, header, inverterProcessors)) != null) {
                articleService.save(article);
            }

        }
        finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
    }

    private static CellProcessor[] getArticlesProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new Optional(), // identifier
                new Optional(), // header
                new Optional(), // description
                new Optional(), // text
                new Optional(), // keywords
                new Optional(), // authors
        };
        return processors;
    }
}