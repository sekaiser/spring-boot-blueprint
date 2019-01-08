package de.seka.news;

import de.seka.news.test.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Unit test the application.
 */
@Category(UnitTest.class)
public class NewsApplicationUnitTests {

    /**
     * Test that the Spring application can be created.
     */
    @Test
    public void canConstruct() {
        MatcherAssert.assertThat(new NewsApplication(), Matchers.notNullValue());
    }

}

