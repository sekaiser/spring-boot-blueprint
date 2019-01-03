package de.seka.news.common.exceptions;

import de.seka.news.test.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(ERROR_CODE, is(me.getErrorCode()));
        assertThat(ERROR_MESSAGE, is(me.getMessage()));
        assertThat(IOE, is(me.getCause()));
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
        assertThat(ERROR_CODE, is(me.getErrorCode()));
        assertThat(ERROR_MESSAGE, is(me.getMessage()));
        assertThat(me.getCause(), is(nullValue()));
        throw me;
    }
}
