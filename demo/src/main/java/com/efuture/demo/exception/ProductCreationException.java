package com.efuture.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductCreationException extends ResponseStatusException {

    public ProductCreationException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
