package com.wants.market.exception;

public class PasswordNotMatchedException extends IllegalArgumentException {

    public PasswordNotMatchedException() {

    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
