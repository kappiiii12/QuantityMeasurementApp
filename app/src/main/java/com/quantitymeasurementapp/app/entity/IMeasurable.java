package com.quantitymeasurementapp.app.entity;

import com.quantitymeasurementapp.app.service.SupportsArithmetic;

public interface IMeasurable {
	
	SupportsArithmetic supportsArithmetic = ()-> true;
	
	double getConversionFactor();

	default double convertToBaseUnit(double value) {
		return getConversionFactor()* value;
	};
	
	default double convertFromBaseUnit(double value) {
		return value / getConversionFactor();
	}
	
	String getUnitName();
	
	default boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
		}
    default void validateOperationSupport(String operation) {
		
	}
}
