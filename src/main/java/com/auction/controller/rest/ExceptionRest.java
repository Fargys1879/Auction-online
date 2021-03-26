package com.auction.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ExceptionRest extends Exception{
    public ExceptionRest(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionRest(String message) {
        super(message);
    }
}
