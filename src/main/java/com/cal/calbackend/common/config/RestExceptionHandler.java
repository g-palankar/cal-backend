package com.cal.calbackend.common.config;

import com.cal.calbackend.common.exception.ApplicationException;
import com.cal.calbackend.common.model.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class, ApplicationException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex) {
        HttpStatus status = (ex instanceof ApplicationException) ? ((ApplicationException) ex).getStatusCode() : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), status);
    }

}
