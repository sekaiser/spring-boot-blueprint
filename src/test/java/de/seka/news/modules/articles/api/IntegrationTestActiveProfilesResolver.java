package de.seka.news.modules.articles.api;

import com.google.common.collect.Sets;
import org.springframework.test.context.ActiveProfilesResolver;

import java.util.Set;

/**
 * A class to switch the active profiles for integration tests based on environment variables.
 */
public class IntegrationTestActiveProfilesResolver implements ActiveProfilesResolver {

    private static final String DB_SELECTOR_ENV_VARIABLE_NAME = "INTEGRATION_TEST_DB";
    private static final String MYSQL = "mysql";
    private static final String POSTGRESQL = "postgresql";
    private static final String H2 = "h2";
    private final Set<String> knownDatabaseProfiles = Sets.newHashSet(
            MYSQL,
            POSTGRESQL,
            H2
    );

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] resolve(final Class<?> testClass) {
        final String integrationTestDatabase = System.getProperty(DB_SELECTOR_ENV_VARIABLE_NAME, H2);

        if (!this.knownDatabaseProfiles.contains(integrationTestDatabase)) {
            throw new IllegalStateException("Unknown database profile: " + integrationTestDatabase);
        }

        return new String[]{
                "integration",
                "db",
                "db-" + integrationTestDatabase,
        };
    }
}
