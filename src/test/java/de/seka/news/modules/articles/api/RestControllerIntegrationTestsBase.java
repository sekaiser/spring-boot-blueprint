package de.seka.news.modules.articles.api;

import de.seka.news.NewsTestApplication;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.test.categories.IntegrationTest;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.junit.Rule;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Base class for all integration tests for the controllers.
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(resolver = IntegrationTestActiveProfilesResolver.class)
public abstract class RestControllerIntegrationTestsBase {

    static final String ARTICLES_API = "/v1/articles";
    static final String LINKS_PATH = "_links";
    static final String EMBEDDED_PATH = "_embedded";
    // Link Keys
    static final String SELF_LINK_KEY = "self";
    private static final String URI_HOST = "news.example.com";
    private static final String URI_SCHEME = "https";

    /**
     * Setup for the Spring Rest Docs wiring.
     */
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @LocalServerPort
    protected int port;

    @Autowired
    protected JpaArticleRepository articleRepository;

    @Getter
    private RequestSpecification requestSpecification;

    /**
     * Clean out the db after every test.
     *
     * @throws Exception on error
     */
    public void cleanup() throws Exception {
        this.articleRepository.deleteAll();
    }
}
