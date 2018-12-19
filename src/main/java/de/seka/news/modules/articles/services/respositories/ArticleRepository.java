package de.seka.news.modules.articles.services.respositories;

import de.seka.news.modules.articles.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Query(value = "SELECT a FROM #{#entityName} t "
            + "WHERE t.keywords LIKE %:keywords%"
            + "ORDER BY t.lastUpdate DESC")
    List<Article> findByKeywords(@Param(value = "keywords") String keywords);
}
