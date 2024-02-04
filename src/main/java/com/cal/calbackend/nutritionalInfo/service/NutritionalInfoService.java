package com.cal.calbackend.nutritionalInfo.service;

import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import com.cal.calbackend.nutritionalInfo.repository.NutritionalInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class NutritionalInfoService {
    @Autowired
    NutritionalInfoRepository nutritionalInfoRepository;

    public NutritionalInfo save(NutritionalInfo nutritionalInfo){
        return this.nutritionalInfoRepository.save(nutritionalInfo);
    }

    public NutritionalInfo update(NutritionalInfo nutritionalInfo){
        return this.save(nutritionalInfo);
    }

    public boolean doesIdExist(Long id){
        return nutritionalInfoRepository.existsById(id);
    }
}
