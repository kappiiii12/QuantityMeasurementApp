package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitFactory {

    @SuppressWarnings("unchecked")
    private static final List<Class<? extends Enum<? extends IMeasurable>>> UNIT_CLASSES =
        List.of(LengthUnit.class, WeightUnit.class, VolumeUnit.class, TemperatureUnit.class);

    public IMeasurable getUnit(String unitName) {
        if (unitName == null || unitName.isBlank())
            throw new IllegalArgumentException("Unit name must not be blank.");

        for (Class<? extends Enum<? extends IMeasurable>> clazz : UNIT_CLASSES) {
            for (Enum<? extends IMeasurable> constant : clazz.getEnumConstants()) {
                if (constant.name().equalsIgnoreCase(unitName)) {
                    return (IMeasurable) constant;
                }
            }
        }
        throw new IllegalArgumentException("Unknown unit: '" + unitName + "'.");
    }
}