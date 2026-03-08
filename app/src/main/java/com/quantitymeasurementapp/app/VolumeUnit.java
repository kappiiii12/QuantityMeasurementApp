package com.quantitymeasurementapp.app;

public enum VolumeUnit implements IMeasurable {
	LITRE(1.0),			// base unit
	MILLILITRE(0.001),
	GALLON(3.78541);
	
	private final double conversionFactor;
	
     VolumeUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	@Override
	public double getConversionFactor() {
		// TODO Auto-generated method stub
		return this.conversionFactor;
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return this.name();
	}

}
