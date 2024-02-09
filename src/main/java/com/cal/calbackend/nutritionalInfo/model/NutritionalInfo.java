package com.cal.calbackend.nutritionalInfo.model;

import com.cal.calbackend.nutritionalInfo.utils.UnitDeserializer;
import com.cal.calbackend.nutritionalInfo.utils.UnitSerializer;
import com.cal.calbackend.nutritionalInfo.utils.UnitType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.measure.Unit;

@Entity
@Data
@NoArgsConstructor
public class NutritionalInfo {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Please provide a name")
    @Size(max = 200, message = "Name can contain a maximum of 200 characters")
    private String name;

    @NotNull(message = "Number of calories is required")
    private Double energyInKiloCalories;

    @NotNull(message = "Quantity is required")
    private Double quantity;

    @JsonDeserialize(using = UnitDeserializer.class)
    @JsonSerialize(using = UnitSerializer.class)
    @NotNull(message = "Measure unit is required")
    @Type(value = UnitType.class)
    private Unit unit;

    private Double proteinInGrams;

    private Double carbohydratesInGrams;

    private Double fatsInGrams;

    private Double fibreInGrams;
}
