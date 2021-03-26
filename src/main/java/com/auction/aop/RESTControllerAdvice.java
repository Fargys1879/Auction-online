package com.auction.aop;

import com.auction.controller.rest.ExceptionRest;
import com.auction.controller.rest.ProductRESTController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackageClasses = ProductRESTController.class)
public class RESTControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionRest.class)
    @ResponseBody
    ResponseEntity<?> handleControllerExceptionRest(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    private class CustomErrorType {
        public CustomErrorType(int value, String message) {
        }
    }
}
