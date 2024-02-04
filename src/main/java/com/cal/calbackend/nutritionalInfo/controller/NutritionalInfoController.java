package com.cal.calbackend.nutritionalInfo.controller;

import com.cal.calbackend.common.model.ErrorResponse;
import com.cal.calbackend.common.model.SuccessResponse;
import com.cal.calbackend.common.utils.StandardResponse;
import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import com.cal.calbackend.nutritionalInfo.service.NutritionalInfoService;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;

@RestController
@RequestMapping("nutrition")
public class NutritionalInfoController {
    @Autowired
    NutritionalInfoService nutritionalInfoService;

    @PostMapping("nutritional-info")
    public ResponseEntity createNutritionalInfo(
        @RequestBody @Valid NutritionalInfo nutritionalInfo, BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) return StandardResponse.generateValidationErrorResponse(bindingResult);
        NutritionalInfo newInfo = this.nutritionalInfoService.save(nutritionalInfo);

        return StandardResponse.generateSuccessResponse("Added successfully.", newInfo, HttpStatus.CREATED);
    }

    @PutMapping("nutritional-info")
    public ResponseEntity updateNutritionalInfo(
            @RequestBody @Valid NutritionalInfo nutritionalInfo,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) return StandardResponse.generateValidationErrorResponse(bindingResult);
        if(nutritionalInfo.getId() == null) return StandardResponse.generateErrorResponse("Item Id is required for update.");
        if(!nutritionalInfoService.doesIdExist(nutritionalInfo.getId())){
            StandardResponse.generateErrorResponse("The item was not found.");
        }
        return StandardResponse.generateSuccessResponse("Successfully updated.", nutritionalInfoService.update(nutritionalInfo));
    }

}
