package com.quantitymeasurementapp.app;

public enum WeightUnit {
	KILOGRAM(1),
	GRAM(1000),
	POUND(0.453592);

  private final double conversionFactor ;
	
	WeightUnit(double conversionFactor){
		this.conversionFactor = conversionFactor;
		
	}
	public double convertToBase(double value) {
		return value / this.conversionFactor;
		}
		public double getConversionFactor() {
			return this.conversionFactor;
		}
	
}
