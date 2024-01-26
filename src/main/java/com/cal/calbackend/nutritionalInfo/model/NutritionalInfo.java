package com.cal.calbackend.nutritionalInfo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Mass;

@Entity
@Data
@NoArgsConstructor
public class NutritionalInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double energyInKiloCalories;
    private Double quantity;

    @Type(value = UnitType.class)
    private Unit unit;
    private Double proteinInGrams;
    private Double carbohydratesInGrams;
    private Double fatsInGrams;
    private Double fibreInGrams;
}
