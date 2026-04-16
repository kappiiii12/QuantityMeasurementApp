package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;

/**
 * Base unit: INCHES
 */
public enum LengthUnit implements IMeasurable {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override public double getConversionFactor() { return conversionFactor; }
    @Override public String getUnitName()         { return name(); }
}