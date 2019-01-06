package de.seka.news.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.apache.tomcat.util.buf.StringUtils;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Read only data transfer object representing an Article.
 */
@Getter
@JsonDeserialize(builder = Article.Builder.class)
public class Article extends CommonDto {

    private static final long serialVersionUID = 7531684792757418164L;

    @NotNull(message = "A valid header is required")
    @Size(max = 255, message = "Max length of the status message is 255 characters")
    private final String header;
    @NotNull(message = "A valid description is required")
    private final String description;
    @NotNull(message = "A valid text is required")
    private final String text;
    @Size(max = 255, message = "Max length of keywords is 255 characters")
    private final String keywords;
    @Size(max = 255, message = "Max length of authors is 255 characters")
    private final String authors;

    /**
     * Constructor used by the builder.
     *
     * @param builder The builder to use
     */
    protected Article(@Valid final Builder builder) {
        super(builder);
        this.keywords = builder.bKeywords.isEmpty()
                ? null
                : StringUtils.join(builder.bKeywords, ',');
        this.authors = builder.bAuthors.isEmpty()
                ? null
                : StringUtils.join(builder.bAuthors, ',');
        this.header = builder.bHeader;
        this.description = builder.bDescription;
        this.text = builder.bText;
    }

    /**
     * Get the keywords.
     *
     * @return The command arguments
     */
    public Optional<String> getKeywords() {
        return Optional.ofNullable(this.keywords);
    }

    public Optional<String> getAuthors() {
        return Optional.ofNullable(this.authors);
    }

    /**
     * A builder to create jobs.
     *
     * @author tgianos
     * @since 3.0.0
     */
    public static class Builder extends CommonDto.Builder<Builder> {

        private final List<String> bAuthors;
        private final List<String> bKeywords;
        private String bHeader;
        private String bDescription;
        private String bText;

        /**
         * Constructor which has required fields.
         *
         * @param name    The name to use for the Job
         * @param user    The user to use for the Job
         * @param version The version to use for the Job
         */
        @JsonCreator
        public Builder(
                @JsonProperty("name") final String name,
                @JsonProperty("user") final String user,
                @JsonProperty("version") final String version
        ) {
            super(name, user, version);
            this.bAuthors = new ArrayList<>();
            this.bKeywords = new ArrayList<>();
        }

        /**
         * The command arguments to use in conjunction with the command executable selected for this job.
         *
         * @param authors The command args
         * @return The builder
         */
        public Builder withAuthors(@Nullable final String authors) {
            this.bAuthors.clear();
            final List<String> listOfAuthors = Arrays.asList(authors.split(","));
            if (!listOfAuthors.isEmpty()) {
                this.bAuthors.addAll(listOfAuthors);
            }
            return this;
        }

        public Builder withKeywords(@Nullable final String keywords) {
            this.bKeywords.clear();
            final List<String> listOfKeywords = Arrays.asList(keywords.split(","));
            if (!listOfKeywords.isEmpty()) {
                this.bKeywords.addAll(listOfKeywords);
            }
            return this;
        }

        public Builder withHeader(final String header) {
            this.bHeader = header;
            return this;
        }

        public Builder withDescription(final String description) {
            this.bDescription = description;
            return this;
        }

        public Builder withText(final String text) {
            this.bText = text;
            return this;
        }

        /**
         * Build the job.
         *
         * @return Create the final read-only Job instance
         */
        public Article build() {
            return new Article(this);
        }
    }
}
