package com.com.mediaalterations.gateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {

    private final String message;

    public UnauthorizedAccessException(String message) {
        this.message = message;
    }
}
