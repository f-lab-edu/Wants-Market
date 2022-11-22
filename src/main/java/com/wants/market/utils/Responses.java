package com.wants.market.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Responses {

    public static final ResponseEntity CREATED = new ResponseEntity(HttpStatus.CREATED);
    public static final ResponseEntity CONFLICT = new ResponseEntity(HttpStatus.CONFLICT);

    public static final ResponseEntity RESPONSE_ENTITY_OK = new ResponseEntity(HttpStatus.OK);
}
