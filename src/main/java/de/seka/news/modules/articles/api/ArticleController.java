package de.seka.news.modules.articles.api;

import de.seka.news.common.dto.Article;
import de.seka.news.common.exceptions.MttrbitException;
import de.seka.news.modules.articles.services.ArticlePersistenceService;
import de.seka.news.hateoas.assemblers.ArticleResourceAssembler;
import de.seka.news.hateoas.resources.ArticleResource;
import de.seka.news.modules.articles.services.ArticleSearchService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint for articles.
 */
@RestController
@RequestMapping({"/articles", "/v1/articles"})
@Slf4j
public class ArticleController {
    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private final ArticleSearchService articleSearchService;
    private final ArticlePersistenceService articlePersistenceService;
    private final ArticleResourceAssembler assembler;

    /**
     * Constructor.
     *
     * @param articleSearchService      The article search service to use
     * @param articlePersistenceService The article persistence service to use
     * @param assembler                 Assemble article resources out of articles
     */
    @Autowired
    public ArticleController(
            final ArticleSearchService articleSearchService,
            final ArticlePersistenceService articlePersistenceService,
            final ArticleResourceAssembler assembler
    ) {
        this.articleSearchService = articleSearchService;
        this.articlePersistenceService = articlePersistenceService;
        this.assembler = assembler;
    }

    /**
     * Submit a new article.
     *
     * @param article            The article information
     * @param clientHost         The client host sending the request
     * @param userAgent          The user agent string
     * @param httpServletRequest The http servlet request
     * @return The submitted article
     * @throws MttrbitException For any error
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> submitArticle(
            @Valid @RequestBody final Article article,
            @RequestHeader(value = FORWARDED_FOR_HEADER, required = false) final String clientHost,
            @RequestHeader(value = HttpHeaders.USER_AGENT, required = false) final String userAgent,
            final HttpServletRequest httpServletRequest
    ) throws MttrbitException {
        log.info("[submitArticle] Called method to submit article: {}", article);
        return handleSubmitArticle(article, clientHost, userAgent, httpServletRequest);
    }

    /**
     * Fetch the article to an id.
     *
     * @param uniqueId The uuid of the article
     * @return the article resource
     * @throws MttrbitException if there was an error
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ArticleResource getArticle(@PathVariable(name = "id") final String uniqueId) throws MttrbitException {
        return assembler.toResource(articleSearchService.getArticle(uniqueId));
    }

    private ResponseEntity<Void> handleSubmitArticle(
            final Article article,
            final String clientHost,
            final String userAgent,
            final HttpServletRequest httpServletRequest
    ) throws MttrbitException {
        final Article articleWithId;
        final Optional<String> articleIdOptional = article.getId();
        if (articleIdOptional.isPresent() && StringUtils.isNotBlank(articleIdOptional.get())) {
            articleWithId = article;
        } else {
            final String articleId = UUID.randomUUID().toString();
            final Article.Builder builder = new Article.Builder(
                    article.getName(),
                    article.getUser(),
                    article.getVersion()
            )
                    .withId(articleId)
                    .withHeader(article.getHeader())
                    .withDescription(article.getDescription())
                    .withText(article.getText());

            article.getAuthors().ifPresent(builder::withAuthors);
            article.getKeywords().ifPresent(builder::withKeywords);
            articleWithId = builder.build();
        }

        articlePersistenceService.createArticle(articleWithId);
        log.info("[submitArticle] submitted article: {}", articleWithId);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(article.getId())
                        .toUri()
        );

        return new ResponseEntity<>(httpHeaders, HttpStatus.ACCEPTED);
    }
}
