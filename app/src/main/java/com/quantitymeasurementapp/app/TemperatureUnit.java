package com.quantitymeasurementapp.app;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {
	CELSIUS(false),
	FAHRENHEIT(true);

	 private final boolean isFahrenheit;

	    TemperatureUnit(boolean isFahrenheit) {
	        this.isFahrenheit = isFahrenheit;
	    }
	    private static final Function<Double, Double> FAHRENHEIT_TO_CELSIUS =
	            (f) -> (f - 32) * 5 / 9;

	    private static final Function<Double, Double> CELSIUS_TO_CELSIUS =
	            (c) -> c;
	            
	            @Override
	            public double convertToBaseUnit(double value) {
	                return isFahrenheit
	                        ? FAHRENHEIT_TO_CELSIUS.apply(value)
	                        : CELSIUS_TO_CELSIUS.apply(value);
	            }
	            public double convertTo(double value, TemperatureUnit targetUnit) {
	                if (targetUnit == null) {
	                    throw new IllegalArgumentException("Target unit cannot be null");
	                }

	                double baseValue = this.convertToBaseUnit(value);
	                return targetUnit.convertFromBaseUnit(baseValue);
	            }
	            public boolean supportsArithmetic() {
	                return supportsArithmetic.isSupported();
	            }

	            @Override
	            public void validateOperationSupport(String operation) {
	                throw new UnsupportedOperationException(
	                        "Temperature does not support " + operation + " operation."
	                );
	            }

	            @Override
	            public double convertFromBaseUnit(double baseValue) {
	                if (isFahrenheit) {
	                    return (baseValue * 9 / 5) + 32;
	                }
	                return baseValue;
	            }
	            @Override
	            public String toString() {
	                return name();
	            }
	@Override
	public double getConversionFactor() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return this.name();
	}

}
