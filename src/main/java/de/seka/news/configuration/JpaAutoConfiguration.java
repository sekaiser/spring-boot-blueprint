package de.seka.news.configuration;

import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.modules.articles.jpa.services.JpaArticlePersistenceServiceImpl;
import de.seka.news.modules.articles.jpa.services.JpaArticleSearchServiceImpl;
import de.seka.news.modules.articles.services.ArticlePersistenceService;
import de.seka.news.modules.articles.services.ArticleSearchService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("de.seka.news.modules.articles.jpa.repositories")
@EntityScan("de.seka.news.modules.articles.jpa.entities")
public class JpaAutoConfiguration {

    /**
     * Get a JPA implementation of the {@link ArticleSearchService} if one didn't already exist.
     *
     * @param articleRepository     The repository to use for article entities
     * @return A {@link JpaArticleSearchServiceImpl} instance
     */
    @Bean
    @ConditionalOnMissingBean(ArticleSearchService.class)
    public JpaArticleSearchServiceImpl articleSearchService(
            final JpaArticleRepository articleRepository
    ) {
        return new JpaArticleSearchServiceImpl(articleRepository);
    }

    /**
     * Get a JPA implementation of the {@link ArticlePersistenceService} if one didn't already exist.
     *
     * @param articleRepository     The repository to use for article entities
     * @return A {@link JpaArticlePersistenceServiceImpl} instance
     */
    @Bean
    @ConditionalOnMissingBean(ArticlePersistenceService.class)
    public JpaArticlePersistenceServiceImpl articlePersistenceService(
            final JpaArticleRepository articleRepository
    ) {
        return new JpaArticlePersistenceServiceImpl(articleRepository);
    }
}
