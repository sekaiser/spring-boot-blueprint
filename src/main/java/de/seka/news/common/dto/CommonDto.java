package de.seka.news.common.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import de.seka.news.common.util.MttrbitObjectMapper;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public abstract class CommonDto extends BaseDto {

    private static final long serialVersionUID = -4120210388801457834L;

    @NotEmpty(message = "A version is required and must be at most 255 characters.")
    @Size(max = 255, message = "The version can be no longer than 255 characters")
    private final String version;
    @NotEmpty(message = "A user is required and must be at most 255 characters")
    @Size(max = 255, message = "The user can be no longer than 255 characters")
    private final String user;
    @NotEmpty(message = "A name is required and must be at most 255 characters")
    @Size(max = 255, message = "The name can be no longer than 255 characters")
    private final String name;
    @Size(max = 1000, message = "The description can be no longer than 1000 characters")
    private final String description;
    private final JsonNode metadata;

    /**
     * Constructor.
     *
     * @param builder The builder to use
     */
    CommonDto(final Builder builder) {
        super(builder);
        this.name = builder.bName;
        this.user = builder.bUser;
        this.version = builder.bVersion;
        this.description = builder.bDescription;
        this.metadata = builder.bMetadata;
    }

    /**
     * Get the description.
     *
     * @return The description as an optional
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    /**
     * Get the metadata of this resource as a JSON Node.
     *
     * @return Optional of the metadata if it exists
     */
    public Optional<JsonNode> getMetadata() {
        return Optional.ofNullable(this.metadata);
    }

    /**
     * Builder pattern to save constructor arguments.
     *
     * @param <T> Type of builder that extends this
     * @author tgianos
     * @since 3.0.0
     */
    // NOTE: These abstract class builders are marked public not protected due to a JDK bug from 1999 which caused
    //       issues with Clojure clients which use reflection to make the Java API calls.
    //       http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4283544
    //       Setting them to public seems to have solved the issue at the expense of "proper" code design
    @SuppressWarnings("unchecked")
    public abstract static class Builder<T extends Builder> extends BaseDto.Builder<T> {

        private final String bName;
        private final String bUser;
        private final String bVersion;
        private String bDescription;
        private JsonNode bMetadata;
        private Set<String> bTags = new HashSet<>();

        protected Builder(final String name, final String user, final String version) {
            this.bName = name;
            this.bUser = user;
            this.bVersion = version;
        }

        /**
         * Set the description for the resource.
         *
         * @param description The description to use
         * @return The builder
         */
        public T withDescription(@Nullable final String description) {
            this.bDescription = description;
            return (T) this;
        }

        /**
         * With the metadata to set for the job as a JsonNode.
         *
         * @param metadata The metadata to set
         * @return The builder
         */
        @JsonSetter
        public T withMetadata(@Nullable final JsonNode metadata) {
            this.bMetadata = metadata;
            return (T) this;
        }

        /**
         * With the metadata to set for the job as a string of valid JSON.
         *
         * @param metadata The metadata to set. Must be valid JSON
         * @return The builder
         * @throws Exception On invalid JSON
         */
        public T withMetadata(@Nullable final String metadata) throws Exception {
            if (metadata == null) {
                this.bMetadata = null;
            } else {
                try {
                    this.bMetadata = MttrbitObjectMapper.getMapper().readTree(metadata);
                } catch (final IOException ioe) {
                    throw new Exception("Invalid JSON string passed in " + metadata, ioe);
                }
            }
            return (T) this;
        }
    }
}
