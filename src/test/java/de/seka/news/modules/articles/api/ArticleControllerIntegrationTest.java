package de.seka.news.modules.articles.api;

import de.seka.news.common.dto.Article;
import de.seka.news.common.util.MttrbitObjectMapper;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;

@Slf4j
public class ArticleControllerIntegrationTest extends RestControllerIntegrationTestsBase {

    private static final String ARTICLES_LIST_PATH = EMBEDDED_PATH + ".articlesList";

    private static final String ARTICLE_NAME = "Create test article";
    private static final String ARTICLE_USER = "seka";
    private static final String ARTICLE_VERSION = "1.0.0";

    /**
     * {@inheritDoc}
     */
    @Before
    @Override
    public void setup() throws Exception {
        super.setup();
    }

    /**
     * {@inheritDoc}
     */
    @After
    @Override
    public void cleanup() throws Exception {
        super.cleanup();
    }

    @Test
    public void makeSureThatGoogleIsUp() {
        RestAssured.given().when().get("http://www.google.com").then().statusCode(200);
        RestAssured.given(this.getRequestSpecification()).when().port(this.port).auth().basic("user", "S123456789!").get("/articles/search").then().statusCode(200);
    }

    /**
     * Test the article submit method for success.
     *
     * @throws Exception If there is a problem.
     */
    @Test
    public void testSubmitArticleMethodSuccess() throws Exception {
        this.submitAndCheckArticle(1);
    }

    private void submitAndCheckArticle(final int documentationId) throws Exception {

        final Article articleRequest = new Article.Builder(
                ARTICLE_NAME,
                ARTICLE_USER,
                ARTICLE_VERSION
        )
                .withAuthors("Foo")
                .withKeywords("test")
                .withDescription("My description")
                .withHeader("Test Header")
                .withText("A sample text")
                .build();

        final String id = this.submitArticle(documentationId, articleRequest);
    }

    private String submitArticle(
            final int documentationId,
            final Article article
    ) throws Exception {
        final RestDocumentationFilter createResultFilter = RestAssuredRestDocumentation.document(
                "{class-name}/" + documentationId + "/articles",
                Snippets.CONTENT_TYPE_HEADER, // Request headers
                Snippets.getJobRequestRequestPayload(), // Request Fields
                Snippets.LOCATION_HEADER // Response Headers
        );

        return this.getIdFromLocation(
                RestAssured
                        .given(this.getRequestSpecification())
                        //.filter(createResultFilter)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(MttrbitObjectMapper.getMapper().writeValueAsBytes(article))
                        .when()
                        .port(this.port)
                        .auth()
                        .basic("user", "S123456789!")
                        .post("/articles")
                        .then()
                        .statusCode(Matchers.is(HttpStatus.ACCEPTED.value()))
                        .header(HttpHeaders.LOCATION, Matchers.notNullValue())
                        .extract()
                        .header(HttpHeaders.LOCATION)
        );
    }

    private void checkFindArticles(final String documentationId, final String id, final String user) {
        final RestDocumentationFilter findResultFilter = RestAssuredRestDocumentation.document(
                "{class-name}/" + documentationId + "/findArticles/",
                Snippets.ARTICLE_SEARCH_QUERY_PARAMETERS, // Request query parameters
                Snippets.HAL_CONTENT_TYPE_HEADER, // Response headers
                Snippets.ARTICLE_SEARCH_RESULT_FIELDS, // Result fields
                Snippets.SEARCH_LINKS // HAL Links
        );

        RestAssured
                .given(this.getRequestSpecification())
                .filter(findResultFilter)
                .param("user", user)
                .when()
                .port(this.port)
                .get(ARTICLES_API)
                .then()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .contentType(Matchers.is(MediaTypes.HAL_JSON_UTF8_VALUE))
                .body(ARTICLES_LIST_PATH, Matchers.hasSize(1))
                .body(ARTICLES_LIST_PATH + "[0].id", Matchers.is(id));

    }
}
