package com.cal.calbackend.nutritionalInfo.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import tech.units.indriya.unit.Units;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NutritionalInfoTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void validNutritionalInfo() {
        NutritionalInfo nutritionalInfo = new NutritionalInfo();
        nutritionalInfo.setName("Example");
        nutritionalInfo.setEnergyInKiloCalories(200.0);
        nutritionalInfo.setQuantity(150.0);
        nutritionalInfo.setUnit(Units.GRAM);

        assertTrue(validator.validate(nutritionalInfo).isEmpty());
    }

    @Test
    void invalidNutritionalInfo(){
        NutritionalInfo nutritionalInfo = new NutritionalInfo();
        Set<ConstraintViolation<NutritionalInfo>> violations = validator.validate(nutritionalInfo);

        assertTrue(hasViolationForProperty(violations, "name"));
        assertTrue(hasViolationForProperty(violations, "energyInKiloCalories"));
        assertTrue(hasViolationForProperty(violations, "quantity"));
        assertTrue(hasViolationForProperty(violations, "unit"));
    }

    private boolean hasViolationForProperty(Set<ConstraintViolation<NutritionalInfo>> violations, String propertyName) {
        return violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals(propertyName));
    }
}