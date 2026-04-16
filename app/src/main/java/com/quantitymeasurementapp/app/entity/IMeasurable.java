package com.quantitymeasurementapp.app.entity;

public interface IMeasurable {

    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return getConversionFactor() * value;
    }

    default double convertFromBaseUnit(double value) {
        return value / getConversionFactor();
    }

    String getUnitName();

    default boolean supportsArithmetic() {
        return true;
    }

    default void validateArithmeticSupport(String operation) {
        // Override in units that do not support arithmetic (e.g., Temperature)
    }
}