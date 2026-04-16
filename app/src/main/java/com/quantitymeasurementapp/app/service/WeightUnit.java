package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;

/**
 * Base unit: GRAM
 */
public enum WeightUnit implements IMeasurable {

    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1_000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override public double getConversionFactor() { return conversionFactor; }
    @Override public String getUnitName()         { return name(); }
}