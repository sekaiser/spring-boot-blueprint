package de.seka.news.common.exceptions;

import java.net.HttpURLConnection;

/**
 * Extension of a MttrbitException for all conflict failures.
 */
public class MttrbitConflictException extends MttrbitException {

    /**
     * Constructor.
     *
     * @param msg human readable message
     * @param cause reason for this exception
     */
    public MttrbitConflictException(final String msg, final Throwable cause) {
        super(HttpURLConnection.HTTP_CONFLICT, msg, cause);
    }

    /**
     * Constructor.
     *
     * @param msg human readable message
     */
    public MttrbitConflictException(final String msg) {
        super(HttpURLConnection.HTTP_CONFLICT, msg);
    }
}
