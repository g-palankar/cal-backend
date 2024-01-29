package com.cal.calbackend.nutritionalInfo.repository;

import com.cal.calbackend.nutritionalInfo.model.NutritionalInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tech.units.indriya.unit.Units;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class NutritionalInfoRepositoryTest {
    @Autowired
    NutritionalInfoRepository nutritionalInfoRepository;

    @Test
    public void whenExistsById_thenReturnTrueForExistingUserId(){
        NutritionalInfo nutritionalInfo = new NutritionalInfo();
        nutritionalInfo.setName("Apple");
        nutritionalInfo.setQuantity(100.0);
        nutritionalInfo.setUnit(Units.GRAM);
        nutritionalInfo.setEnergyInKiloCalories(200.0);

        NutritionalInfo saved = nutritionalInfoRepository.save(nutritionalInfo);

        boolean doesExist = this.nutritionalInfoRepository.existsById(saved.getId());

        assertTrue(doesExist);
    }

    @Test
    public void whenExistsById_thenReturnFalseForNonExistingUserId(){
        boolean doesExist = nutritionalInfoRepository.existsById(1L);
        assertFalse(doesExist);

    }
}