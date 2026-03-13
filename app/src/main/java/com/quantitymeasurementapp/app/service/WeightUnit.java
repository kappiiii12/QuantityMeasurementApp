package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.entity.IMeasurable;

public enum WeightUnit implements IMeasurable{
	   MILLIGRAM(0.001),
	    GRAM(1.0),              // Base unit
	    KILOGRAM(1000.0),
	    POUND(453.592),
	    TONNE(1000000);


  private final double conversionFactor ;
	
	WeightUnit(double conversionFactor){
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
