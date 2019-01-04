package de.seka.news.modules.articles.api;

import de.seka.news.NewsTestApplication;
import de.seka.news.modules.articles.jpa.repositories.JpaArticleRepository;
import de.seka.news.test.categories.IntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockSnippet;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Nullable;
import java.util.Set;

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
    private static final String URI_HOST = "api.example.com";
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

    private static String getLinkedResourceExpectedUri(
            final String entityApi,
            final String entityId,
            final Set<String> halParams,
            final String linkedEntityType
    ) {
        final String uriPath = entityApi + "/" + entityId + "/" + linkedEntityType;

        final StringBuilder builder = new StringBuilder();
        if (halParams != null && !halParams.isEmpty()) {
            builder
                    .append("{?")
                    .append(StringUtils.join(halParams, ","))
                    .append("}");
        }

        return uriPath + builder.toString();
    }

    /**
     * Clean out the db before every test.
     *
     * @throws Exception on error
     */
    public void setup() throws Exception {
        this.articleRepository.deleteAll();

        this.requestSpecification = new RequestSpecBuilder()
                .addFilter(
                        RestAssuredRestDocumentation
                                .documentationConfiguration(this.restDocumentation)
                                .snippets().withAdditionalDefaults(new WireMockSnippet())
                                .and()
                                .operationPreprocessors()
                                .withRequestDefaults(
                                        Preprocessors.prettyPrint(),
                                        Preprocessors.modifyUris().scheme(URI_SCHEME).host(URI_HOST).removePort()
                                )
                                .withResponseDefaults(
                                        Preprocessors.prettyPrint(),
                                        Preprocessors.modifyUris().scheme(URI_SCHEME).host(URI_HOST).removePort()
                                )
                )
                .build();
    }

    /**
     * Clean out the db after every test.
     *
     * @throws Exception on error
     */
    public void cleanup() throws Exception {
        this.articleRepository.deleteAll();
    }

    String getIdFromLocation(@Nullable final String location) {
        if (location == null) {
            Assert.fail();
        }
        return location.substring(location.lastIndexOf("/") + 1);
    }
}
