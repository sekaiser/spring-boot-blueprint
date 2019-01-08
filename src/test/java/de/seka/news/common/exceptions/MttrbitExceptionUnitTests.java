package de.seka.news.common.exceptions;

import de.seka.news.test.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Unit test exceptions.
 */
@Category(UnitTest.class)
public class MttrbitExceptionUnitTests {

    private static final int ERROR_CODE = 404;
    private static final String ERROR_MESSAGE = "Not Found";
    private static final IOException IOE = new IOException("IOException");

    /**
     * Test the constructor.
     *
     * @throws MttrbitException On exception
     */
    @Test(expected = MttrbitException.class)
    public void testThreeArgConstructor() throws MttrbitException {
        final MttrbitException me = new MttrbitException(ERROR_CODE, ERROR_MESSAGE, IOE);
        MatcherAssert.assertThat(ERROR_CODE, Matchers.is(me.getErrorCode()));
        MatcherAssert.assertThat(ERROR_MESSAGE, Matchers.is(me.getMessage()));
        MatcherAssert.assertThat(IOE, Matchers.is(me.getCause()));
        throw me;
    }

    /**
     * Test the constructor.
     *
     * @throws MttrbitException On exception
     */
    @Test(expected = MttrbitException.class)
    public void testTwoArgConstructorWithMessage() throws MttrbitException {
        final MttrbitException me = new MttrbitException(ERROR_CODE, ERROR_MESSAGE);
        MatcherAssert.assertThat(ERROR_CODE, Matchers.is(me.getErrorCode()));
        MatcherAssert.assertThat(ERROR_MESSAGE, Matchers.is(me.getMessage()));
        MatcherAssert.assertThat(me.getCause(), Matchers.is(Matchers.nullValue()));
        throw me;
    }
}
