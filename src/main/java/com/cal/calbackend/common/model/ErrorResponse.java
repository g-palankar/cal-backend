package com.cal.calbackend.common.model;

import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {

    private String message;

    private Map<String, String> errors;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, BindingResult bindingResult){
        this(message);
        errors = new HashMap<String, String>();
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
//        bindingResult.getAllErrors().forEach(e -> {this.errors.put(e.(), e.getDefaultMessage());});
    }
}
