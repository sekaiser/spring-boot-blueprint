package de.seka.news.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * A singleton for sharing a Jackson Object Mapper instance across mttrbit and not having to redefine the Object Mapper
 * everywhere.
 */
public final class MttrbitObjectMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));

    /**
     * Constructor.
     */
    private MttrbitObjectMapper() {
    }

    /**
     * Get the preconfigured Object Mapper used across Genie for consistency.
     *
     * @return The object mapper to use
     */
    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
