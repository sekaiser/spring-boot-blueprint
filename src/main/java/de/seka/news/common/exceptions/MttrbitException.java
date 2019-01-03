package de.seka.news.common.exceptions;

/**
 * The common exception class that represents a service failure. It includes an
 * HTTP error code and a human-readable error message.
 */
public class MttrbitException extends Exception {
    private static final long serialVersionUID = 1L;

    /* the HTTP error code */
    private final int errorCode;

    /**
     * Constructor.
     *
     * @param errorCode the HTTP status code for this exception
     * @param msg       human readable message
     * @param cause     reason for this exception
     */
    public MttrbitException(final int errorCode, final String msg, final Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    /**
     * Constructor.
     *
     * @param errorCode the HTTP status code for this exception
     * @param msg       human readable message
     */
    public MttrbitException(final int errorCode, final String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    /**
     * Return the HTTP status code for this exception.
     *
     * @return the HTTP status code
     */
    public int getErrorCode() {
        return errorCode;
    }
}
