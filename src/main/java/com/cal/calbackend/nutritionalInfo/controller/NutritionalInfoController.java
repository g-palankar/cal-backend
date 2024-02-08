package com.cal.calbackend.nutritionalInfo.controller;

import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import com.cal.calbackend.nutritionalInfo.services.NutritionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nutritional-info")
public class NutritionalInfoController {

    @Autowired
    private NutritionalInfoService nutritionalInfoService;

    @PostMapping("/add")
    public ResponseEntity<String> addNutritionalInfo(@RequestBody NutritionalInfo nutritionalInfo) {
        // You can perform additional validation or processing if needed before saving
        nutritionalInfoService.addNutritionalInfo(nutritionalInfo);
        return new ResponseEntity<>("Nutritional Info added successfully", HttpStatus.CREATED);
    }

    // Other endpoints as needed
}
