package com.example.rest.pagination.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
public class InvalidRangeException extends RuntimeException {

    public InvalidRangeException() {
    }

    public InvalidRangeException(String message) {
        super(message);
    }

}


