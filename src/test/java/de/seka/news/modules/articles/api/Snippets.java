package de.seka.news.modules.articles.api;

import de.seka.news.common.dto.Article;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.headers.ResponseHeadersSnippet;
import org.springframework.restdocs.hypermedia.HypermediaDocumentation;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.*;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.PathParametersSnippet;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.request.RequestParametersSnippet;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.util.StringUtils;

final class Snippets {
    static final RequestHeadersSnippet CONTENT_TYPE_HEADER = HeaderDocumentation.requestHeaders(
            HeaderDocumentation.headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaType.APPLICATION_JSON_VALUE)
    );
    static final ResponseHeadersSnippet LOCATION_HEADER = HeaderDocumentation.responseHeaders(
            HeaderDocumentation.headerWithName(HttpHeaders.LOCATION).description("The URI")
    );
    static final ResponseHeadersSnippet HAL_CONTENT_TYPE_HEADER = HeaderDocumentation.responseHeaders(
            HeaderDocumentation.headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaTypes.HAL_JSON_VALUE)
    );
    static final ResponseHeadersSnippet JSON_CONTENT_TYPE_HEADER = HeaderDocumentation.responseHeaders(
            HeaderDocumentation.headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaType.APPLICATION_JSON_VALUE)
    );
    static final PathParametersSnippet ID_PATH_PARAM = RequestDocumentation.pathParameters(
            RequestDocumentation.parameterWithName("id").description("The resource id")
    );
    static final String CONSTRAINTS = "constraints";
    static final Attributes.Attribute EMPTY_CONSTRAINTS = Attributes.key(CONSTRAINTS).value("");
    private static final ConstraintDescriptions JOB_REQUEST_CONSTRAINTS = new ConstraintDescriptions(Article.class);
    private static final ConstraintDescriptions JOB_CONSTRAINTS = new ConstraintDescriptions(Article.class);
    static final LinksSnippet SEARCH_LINKS = HypermediaDocumentation.links(
            HypermediaDocumentation
                    .linkWithRel("self")
                    .description("The current search"),
            HypermediaDocumentation
                    .linkWithRel("first")
                    .description("The first page for this search")
                    .optional(),
            HypermediaDocumentation
                    .linkWithRel("prev")
                    .description("The previous page for this search")
                    .optional(),
            HypermediaDocumentation
                    .linkWithRel("next")
                    .description("The next page for this search")
                    .optional(),
            HypermediaDocumentation
                    .linkWithRel("last")
                    .description("The last page for this search")
                    .optional()
    );
    static final ResponseFieldsSnippet ARTICLE_SEARCH_RESULT_FIELDS = PayloadDocumentation
            .responseFields(
                    ArrayUtils.addAll(
                            getSearchResultFields(),
                            PayloadDocumentation
                                    .subsectionWithPath("_embedded.articleSearchResultList")
                                    .description("The found article.")
                                    .type(JsonFieldType.ARRAY)
                                    .attributes(EMPTY_CONSTRAINTS)
                    )
            );
    static final RequestParametersSnippet ARTICLE_SEARCH_QUERY_PARAMETERS = RequestDocumentation.requestParameters(
            ArrayUtils.addAll(
                    getCommonSearchParameters(),
                    RequestDocumentation
                            .parameterWithName("authors")
                            .description("The authors of the articles to find. Use % to perform a regex like query")
                            .optional(),
                    RequestDocumentation
                            .parameterWithName("keywords")
                            .description("The keywords of the articles to find. Use % to perform a regex like query")
                            .optional()
            )
    );

    static RequestFieldsSnippet getJobRequestRequestPayload() {
        return PayloadDocumentation.requestFields(getJobFieldDescriptors());
    }

    static ResponseFieldsSnippet getJobRequestResponsePayload() {
        return PayloadDocumentation.responseFields(getJobFieldDescriptors())
                .and(
                        PayloadDocumentation
                                .subsectionWithPath("_links")
                                .attributes(
                                        Attributes
                                                .key(CONSTRAINTS)
                                                .value("")
                                )
                                .description("<<_hateoas,Links>> to other resources.")
                                .ignored()
                );
    }

    static ResponseFieldsSnippet getJobResponsePayload() {
        return PayloadDocumentation.responseFields(getJobFieldDescriptors())
                .and(
                        PayloadDocumentation
                                .subsectionWithPath("_links")
                                .attributes(
                                        Attributes
                                                .key(CONSTRAINTS)
                                                .value("")
                                )
                                .description("<<_hateoas,Links>> to other resources.")
                                .ignored()
                );
    }

    private static FieldDescriptor[] getJobFieldDescriptors() {
        return ArrayUtils.addAll(
                getCommonFieldDescriptors(JOB_CONSTRAINTS),
                PayloadDocumentation
                        .fieldWithPath("header")
                        .attributes(getConstraintsForField(JOB_REQUEST_CONSTRAINTS, "header"))
                        .description("The headline of the article.")
                        .type(JsonFieldType.STRING)
                        .optional(),
                PayloadDocumentation
                        .subsectionWithPath("description")
                        .attributes(getConstraintsForField(JOB_REQUEST_CONSTRAINTS, "description"))
                        .description(
                                "A brief description/abstract of the article."
                        )
                        .type(JsonFieldType.STRING),
                PayloadDocumentation
                        .subsectionWithPath("text")
                        .attributes(getConstraintsForField(JOB_REQUEST_CONSTRAINTS, "text"))
                        .description(
                                "The contengt of the article."
                        )
                        .type(JsonFieldType.STRING),
                PayloadDocumentation
                        .subsectionWithPath("keywords")
                        .attributes(getConstraintsForField(JOB_REQUEST_CONSTRAINTS, "keywords"))
                        .description(
                                "List of keywords separated by ','."
                        )
                        .type(JsonFieldType.STRING)
                        .optional(),
                PayloadDocumentation
                        .subsectionWithPath("authors")
                        .attributes(getConstraintsForField(JOB_REQUEST_CONSTRAINTS, "authors"))
                        .description(
                                "List of authors following the pattern firstname lastname separated by ','."
                        )
                        .type(JsonFieldType.STRING)
                        .optional()
        );
    }

    // once pagination is set up
    private static ParameterDescriptor[] getCommonSearchParameters() {
        return new ParameterDescriptor[]{
                RequestDocumentation
                        .parameterWithName("page")
                        .description("The page number to get. Default to 0.")
                        .optional(),
                RequestDocumentation
                        .parameterWithName("size")
                        .description("The size of the page to get. Default to 64.")
                        .optional(),
                RequestDocumentation.parameterWithName("sort")
                        .description("The fields to sort the results by. Defaults to 'updated,desc'.")
                        .optional(),
        };
    }

    private static FieldDescriptor[] getSearchResultFields() {
        return new FieldDescriptor[]{
                PayloadDocumentation
                        .subsectionWithPath("_links")
                        .description("<<_hateoas,Links>> to other resources.")
                        .attributes(EMPTY_CONSTRAINTS),
                PayloadDocumentation
                        .fieldWithPath("page")
                        .description("The result page information.")
                        .attributes(EMPTY_CONSTRAINTS),
                PayloadDocumentation
                        .fieldWithPath("page.size")
                        .description("The number of elements in this page result.")
                        .attributes(EMPTY_CONSTRAINTS),
                PayloadDocumentation
                        .fieldWithPath("page.totalElements")
                        .description("The total number of elements this search result could return.")
                        .attributes(EMPTY_CONSTRAINTS),
                PayloadDocumentation
                        .fieldWithPath("page.totalPages")
                        .description("The total number of pages there could be at the current page size.")
                        .attributes(EMPTY_CONSTRAINTS),
                PayloadDocumentation
                        .fieldWithPath("page.number")
                        .description("The current page number.")
                        .attributes(EMPTY_CONSTRAINTS),
        };
    }

    private static FieldDescriptor[] getCommonFieldDescriptors(final ConstraintDescriptions constraintDescriptions) {
        return ArrayUtils.addAll(
                getBaseFieldDescriptors(constraintDescriptions),
                PayloadDocumentation
                        .fieldWithPath("name")
                        .attributes(getConstraintsForField(constraintDescriptions, "name"))
                        .description("The name")
                        .type(JsonFieldType.STRING),
                PayloadDocumentation
                        .fieldWithPath("user")
                        .attributes(getConstraintsForField(constraintDescriptions, "user"))
                        .description("The user")
                        .type(JsonFieldType.STRING),
                PayloadDocumentation
                        .fieldWithPath("version")
                        .attributes(getConstraintsForField(constraintDescriptions, "version"))
                        .description("The version")
                        .type(JsonFieldType.STRING),
                PayloadDocumentation
                        .subsectionWithPath("metadata")
                        .attributes(getConstraintsForField(constraintDescriptions, "metadata"))
                        .description("Any semi-structured metadata. Must be valid JSON")
                        .type(JsonFieldType.OBJECT)
                        .optional()
        );
    }

    private static FieldDescriptor[] getBaseFieldDescriptors(final ConstraintDescriptions constraintDescriptions) {
        return new FieldDescriptor[]{
                PayloadDocumentation
                        .fieldWithPath("id")
                        .attributes(getConstraintsForField(constraintDescriptions, "id"))
                        .description("The id. If not set the system will set one.")
                        .type(JsonFieldType.STRING)
                        .optional(),
                PayloadDocumentation
                        .fieldWithPath("created")
                        .attributes(getConstraintsForField(constraintDescriptions, "created"))
                        .description("The UTC time of creation. Set by system. ISO8601 format including milliseconds.")
                        .type(JsonFieldType.STRING)
                        .optional(),
                PayloadDocumentation
                        .fieldWithPath("updated")
                        .attributes(getConstraintsForField(constraintDescriptions, "updated"))
                        .description("The UTC time of last update. Set by system. ISO8601 format including milliseconds.")
                        .type(JsonFieldType.STRING)
                        .optional(),
        };
    }

    private static Attributes.Attribute getConstraintsForField(
            final ConstraintDescriptions constraints,
            final String fieldName
    ) {
        return Attributes
                .key(CONSTRAINTS)
                .value(StringUtils.collectionToDelimitedString(constraints.descriptionsForProperty(fieldName), ". "));
    }
}
