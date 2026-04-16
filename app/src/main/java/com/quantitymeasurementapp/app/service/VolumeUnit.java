package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;

/**
 * Base unit: LITRE
 */
public enum VolumeUnit implements IMeasurable {

    MILLILITRE(0.001),
    LITRE(1.0),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override public double getConversionFactor() { return conversionFactor; }
    @Override public String getUnitName()         { return name(); }
}