package com.cal.calbackend.common.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private final HttpStatus status;

    private String message;

    private String debugMessage;

    public ErrorResponse(HttpStatus status) {
        this.status = status;
    }

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getCause() != null ? ex.getCause().getMessage() : "";
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }


}
