package com.quantitymeasurementapp.app;

public enum LengthUnit {
	FEET(12),
	INCHES(1),
	YARDS(36),
	CENTIMETERS(0.393701);
	
	private final double conversionFactor;
	 
	LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	public double convertToBase(double value) {
	return value * this.conversionFactor;
	}
	public double getConversionFactor() {
		return this.conversionFactor;
	}
}
