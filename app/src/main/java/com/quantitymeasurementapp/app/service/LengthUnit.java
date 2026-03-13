package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;

public enum LengthUnit implements IMeasurable {
	FEET(12),
	INCHES(1),
	YARDS(36),
	CENTIMETERS(0.393701);
	
	private final double conversionFactor;
	 
	LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	@Override
	public double getConversionFactor() {
		return this.conversionFactor;
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return this.name();
	}
}
