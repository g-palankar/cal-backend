package com.cal.calbackend.common.config;

import com.cal.calbackend.common.exception.ApplicationException;
import com.cal.calbackend.common.model.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


class RestExceptionHandlerTest {
    RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    @Test
    void whenRuntimeException_handleException() {
        RuntimeException runtimeException = new RuntimeException("Test run time exception");
        ResponseEntity<Object> responseEntity = restExceptionHandler.handleException(runtimeException);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(responseEntity.getBody().getClass(), ErrorResponse.class);
        assertEquals(((ErrorResponse) responseEntity.getBody()).getMessage(), runtimeException.getMessage());
    }

    @Test
    void whenApplicationApplication_handleException(){
        ApplicationException applicationException = new ApplicationException("Test application exception", HttpStatus.CONFLICT, null);
        ResponseEntity<Object> responseEntity = restExceptionHandler.handleException(applicationException);
        assertEquals(responseEntity.getStatusCode(), applicationException.getStatusCode());
        assertEquals(responseEntity.getBody().getClass(), ErrorResponse.class);
        assertEquals(((ErrorResponse) responseEntity.getBody()).getMessage(), applicationException.getMessage());
    }
}