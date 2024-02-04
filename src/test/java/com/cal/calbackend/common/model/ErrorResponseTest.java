package com.cal.calbackend.common.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    public void testErrorResponseWithMessageOnly() {
        String message = "Test message";
        ErrorResponse errorResponse = new ErrorResponse(message);

        assertNotNull(errorResponse);
        assertEquals(message, errorResponse.getMessage());
        assertNull(errorResponse.getErrors());
    }

    @Test
    public void testErrorResponseWithBindingResult() {
        String mainTestMessage = "Test message";
        String testFieldName = "testField";
        String testDefaultMessage = "test default message";

        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("testObject", testFieldName, testDefaultMessage));

        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        ErrorResponse errorResponse = new ErrorResponse(mainTestMessage, bindingResult);

        assertNotNull(errorResponse);
        assertEquals(mainTestMessage, errorResponse.getMessage());
        Map<String, String> errors = errorResponse.getErrors();
        assertNotNull(errors);
        assertEquals(1, errors.size());
        assertEquals(testDefaultMessage, errors.get(testFieldName));
    }

}