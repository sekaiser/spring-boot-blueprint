package de.seka.news.common.exceptions;

import java.net.HttpURLConnection;

public class MttrbitNotFoundException extends MttrbitException {
    /**
     * Constructor.
     *
     * @param msg   human readable message
     * @param cause reason for this exception
     */
    public MttrbitNotFoundException(final String msg, final Throwable cause) {
        super(HttpURLConnection.HTTP_NOT_FOUND, msg, cause);
    }

    /**
     * Constructor.
     *
     * @param msg human readable message
     */
    public MttrbitNotFoundException(final String msg) {
        super(HttpURLConnection.HTTP_NOT_FOUND, msg);
    }
}
