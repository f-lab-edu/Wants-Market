package com.wants.market.exception;

public class DuplicatedIdException extends IllegalArgumentException {

    public DuplicatedIdException(String message) {
        super(message);
    }
}
