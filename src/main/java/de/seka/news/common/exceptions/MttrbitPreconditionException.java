package de.seka.news.common.exceptions;

import java.net.HttpURLConnection;

/**
 * Extension of a MttrbitException for all precondition failures.
 */
public class MttrbitPreconditionException extends MttrbitException {
    /**
     * Constructor.
     *
     * @param msg   human readable message
     * @param cause reason for this exception
     */
    public MttrbitPreconditionException(final String msg, final Throwable cause) {
        super(HttpURLConnection.HTTP_PRECON_FAILED, msg, cause);
    }

    /**
     * Constructor.
     *
     * @param msg human readable message
     */
    public MttrbitPreconditionException(final String msg) {
        super(HttpURLConnection.HTTP_PRECON_FAILED, msg);
    }
}
