package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class UnitFactory {

    private final List<Class<? extends Enum<? extends IMeasurable>>> unitClasses = Arrays.asList(
            LengthUnit.class,
            WeightUnit.class,
            VolumeUnit.class,
            TemperatureUnit.class
    );

    public IMeasurable getUnit(String unitName) {
        for (Class<? extends Enum<? extends IMeasurable>> unitClass : unitClasses) {
            for (Enum<? extends IMeasurable> enumConstant : unitClass.getEnumConstants()) {
                if (enumConstant.name().equalsIgnoreCase(unitName)) {
                    return (IMeasurable) enumConstant;
                }
            }
        }
        throw new IllegalArgumentException("Unknown unit: " + unitName);
    }
}