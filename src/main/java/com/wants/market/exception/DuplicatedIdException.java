package com.wants.market.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicatedIdException extends IllegalArgumentException {

    public DuplicatedIdException(String message) {
        super(message);
    }
}
