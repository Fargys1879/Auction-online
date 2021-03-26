package com.auction.controller.rest;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    private HttpStatus status;
    private String message;
    private Object data;
}
