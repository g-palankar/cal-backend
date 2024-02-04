package com.cal.calbackend.common.utils;

import com.cal.calbackend.common.model.ErrorResponse;
import com.cal.calbackend.common.model.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class StandardResponse<T> {
    public static ResponseEntity<ErrorResponse> generateValidationErrorResponse(BindingResult bindingResult){
        String message = bindingResult.getErrorCount() > 1 ? "There are a few errors. Please correct them to continue." : "Please correct the validation error to continue.";
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(message, bindingResult), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ErrorResponse> generateErrorResponse(String message){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> generateSuccessResponse(String message, T data, HttpStatus status){
        return new ResponseEntity<>(new SuccessResponse<>(message, data), status);
    }
    public static <T> ResponseEntity<SuccessResponse<T>> generateSuccessResponse(String message, T data){
        return generateSuccessResponse(message, data, HttpStatus.OK);
    }

}
