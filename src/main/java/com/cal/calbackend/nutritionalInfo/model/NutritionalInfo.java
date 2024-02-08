package com.cal.calbackend.nutritionalInfo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import com.cal.calbackend.nutritionalInfo.searializer.UnitDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import tech.units.indriya.unit.Units;

import javax.measure.Unit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalInfo {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Positive(message = "Energy in KiloCalories must be a positive value")
    private Double energyInKiloCalories;

    @Positive(message = "Quantity must be a positive value")
    private Double quantity;

    @Type(value = UnitType.class)
    @JsonDeserialize(using = UnitDeserializer.class)
    private Unit unit;

    @Positive(message = "Protein in Grams must be a positive value")
    private Double proteinInGrams;

    @Positive(message = "Carbohydrates in Grams must be a positive value")
    private Double carbohydratesInGrams;

    @Positive(message = "Fats in Grams must be a positive value")
    private Double fatsInGrams;

    @Positive(message = "Fibre in Grams must be a positive value")
    private Double fibreInGrams;
}
