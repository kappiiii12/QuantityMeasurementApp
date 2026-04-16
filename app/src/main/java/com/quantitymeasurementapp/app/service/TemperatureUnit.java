package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;

/**
 * Base unit: CELSIUS
 * Temperature does NOT support add / subtract (physically meaningless).
 */
public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        @Override public double convertToBaseUnit(double v)   { return v; }
        @Override public double convertFromBaseUnit(double v) { return v; }
    },
    FAHRENHEIT {
        @Override public double convertToBaseUnit(double v)   { return (v - 32) * 5.0 / 9.0; }
        @Override public double convertFromBaseUnit(double v) { return (v * 9.0 / 5.0) + 32; }
    },
    KELVIN {
        @Override public double convertToBaseUnit(double v)   { return v - 273.15; }
        @Override public double convertFromBaseUnit(double v) { return v + 273.15; }
    };

    /** Not used for temperature — conversion is non-linear. */
    @Override
    public double getConversionFactor() { return 1.0; }

    @Override
    public String getUnitName() { return name(); }

    @Override
    public boolean supportsArithmetic() { return false; }

    @Override
    public void validateArithmeticSupport(String operation) {
        throw new UnsupportedOperationException(
            "Temperature does not support " + operation + ".");
    }
}