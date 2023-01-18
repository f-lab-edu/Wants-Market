package com.wants.market.exception;

public class UnAuthorizedAccessException extends RuntimeException {

    public UnAuthorizedAccessException() {
        super();
    }

    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}
