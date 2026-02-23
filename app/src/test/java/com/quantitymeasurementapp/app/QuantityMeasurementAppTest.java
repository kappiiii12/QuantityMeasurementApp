package com.quantitymeasurementapp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



/**
 * Unit test for simple
 */
public class QuantityMeasurementAppTest {

    

	 @Test
	 void testConversion_FeetToInches() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(1.0, Length.Unit.FEET, Length.Unit.INCHES);

	     assertEquals(new Length(12.0, Length.Unit.INCHES), result);
	 }

	 @Test
	 void testConversion_InchesToFeet() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(24.0, Length.Unit.INCHES, Length.Unit.FEET);

	     assertEquals(new Length(2.0, Length.Unit.FEET), result);
	 }

	 @Test
	 void testConversion_YardsToInches() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(1.0, Length.Unit.YARDS, Length.Unit.INCHES);

	     assertEquals(new Length(36.0, Length.Unit.INCHES), result);
	 }

	 @Test
	 void testConversion_InchesToYards() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(72.0, Length.Unit.INCHES, Length.Unit.YARDS);

	     assertEquals(new Length(2.0, Length.Unit.YARDS), result);
	 }

	 @Test
	 void testConversion_CentimetersToInches() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(2.54, Length.Unit.CENTIMETERS, Length.Unit.INCHES);

	     assertEquals(new Length(1.0, Length.Unit.INCHES), result);
	 }

	 @Test
	 void testConversion_FeetToYards() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(6.0, Length.Unit.FEET, Length.Unit.YARDS);

	     assertEquals(new Length(2.0, Length.Unit.YARDS), result);
	 }
	 
	 /* =========================================================
	 UC5: Edge Case Tests
	 ========================================================= */
	 
	 @Test
	 void testConversion_ZeroValue() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(0.0, Length.Unit.FEET, Length.Unit.INCHES);

	     assertEquals(new Length(0.0, Length.Unit.INCHES), result);
	 }

	 @Test
	 void testConversion_NegativeValue() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(-1.0, Length.Unit.FEET, Length.Unit.INCHES);

	     assertEquals(new Length(-12.0, Length.Unit.INCHES), result);
	 }

	 @Test
	 void testConversion_SameUnit() {
	     Length result = QuantityMeasurementApp
	             .demonstrateLengthConversion(5.0, Length.Unit.FEET, Length.Unit.FEET);

	     assertEquals(new Length(5.0, Length.Unit.FEET), result);
	 }
	 
	 /* =========================================================
	 UC5: Round Trip Test
	 ========================================================= */
	 
	 @Test
	 void testConversion_RoundTrip_PreservesValue() {
	     Length original = new Length(5.0, Length.Unit.FEET);

	     Length converted = QuantityMeasurementApp
	             .demonstrateLengthConversion(original, Length.Unit.INCHES);

	     Length back = QuantityMeasurementApp
	             .demonstrateLengthConversion(converted, Length.Unit.FEET);

	     assertEquals(original, back);
	 }
	 
	 /* =========================================================
	 UC5: Exception Tests
	 ========================================================= */
	 
	 @Test
	 void testConversion_InvalidUnit_Throws() {
	     assertThrows(IllegalArgumentException.class,
	             () -> QuantityMeasurementApp
	                     .demonstrateLengthConversion(1.0, null, Length.Unit.FEET));
	 }

	 @Test
	 void testConversion_NaN_Throws() {
	     assertThrows(IllegalArgumentException.class,
	             () -> QuantityMeasurementApp
	                     .demonstrateLengthConversion(Double.NaN, Length.Unit.FEET, Length.Unit.INCHES));
	 }

	 @Test
	 void testConversion_Infinite_Throws() {
	     assertThrows(IllegalArgumentException.class,
	             () -> QuantityMeasurementApp
	                     .demonstrateLengthConversion(Double.POSITIVE_INFINITY, Length.Unit.FEET, Length.Unit.INCHES));
	 
 }
}

