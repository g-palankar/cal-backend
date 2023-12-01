package com.cal.calbackend.common.config;

import com.cal.calbackend.common.exception.ApplicationException;
import com.cal.calbackend.common.model.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ApplicationException.class})
    protected ResponseEntity<Object> handleApplicatinException(ApplicationException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getStatusCode(), ex.getMessage(), ex), ex.getStatusCode());
    }
}
