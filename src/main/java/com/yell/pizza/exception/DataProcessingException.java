package com.yell.pizza.exception;

/**
 * Class representing json processing exception.
 */
public class DataProcessingException extends Exception {
    private static final long serialVersionUID = 1L;

    public DataProcessingException() {
        super();
    }

    public DataProcessingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataProcessingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataProcessingException(final String message) {
        super(message);
    }

    public DataProcessingException(final Throwable cause) {
        super(cause);
    }
}
