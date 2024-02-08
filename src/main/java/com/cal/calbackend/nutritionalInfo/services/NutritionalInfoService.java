package com.cal.calbackend.nutritionalInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import com.cal.calbackend.nutritionalInfo.repository.NutritionalInfoRepository;

@Service
public class NutritionalInfoService {

    @Autowired
    private NutritionalInfoRepository nutritionalInfoRepository;

    public void addNutritionalInfo(NutritionalInfo nutritionalInfo) {
        nutritionalInfoRepository.save(nutritionalInfo);
    }
}
