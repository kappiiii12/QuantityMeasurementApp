package com.quantitymeasurementapp.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.app.QuantityMeasurementApp.Feet;

/**
 * Unit test for simple App.
 */
public class QuantityMeasurementAppTest {

    
	 QuantityMeasurementApp qm = new QuantityMeasurementApp();
	 
	 // *************************************** UC1 Test Cases ****************************************************
	   @Test
	    void givenSameFeetValue_shouldReturnTrue() {
	        Feet f1 = new Feet(1.0);
	        Feet f2 = new Feet(1.0);
	        assertTrue(f1.equals(f2));
	    }

	    @Test
	    void givenDifferentFeetValues_shouldReturnFalse() {
	        Feet f1 = new Feet(1.0);
	        Feet f2 = new Feet(2.0);
	        assertFalse(f1.equals(f2));
	    }

	    @Test
	    void givenSameReference_shouldReturnTrue() {
	        Feet f1 = new Feet(1.0);
	        assertTrue(f1.equals(f1));
	    }

	    @Test
	    void givenFeetAndNull_shouldReturnFalse() {
	        Feet f1 = new Feet(1.0);
	        assertFalse(f1.equals(null));
	    }

	    @Test
	    void givenFeetAndDifferentType_shouldReturnFalse() {
	        Feet f1 = new Feet(1.0);
	        assertFalse(f1.equals("1.0"));
	    }

	    @Test
	    void givenTwoDifferentObjectsWithSameValue_shouldReturnTrue() {
	        Feet f1 = new Feet(5.0);
	        Feet f2 = new Feet(5.0);
	        assertTrue(f1.equals(f2));
	    }

	    @Test
	    void givenDecimalFeetValues_shouldReturnTrue() {
	        Feet f1 = new Feet(2.5);
	        Feet f2 = new Feet(2.5);
	        assertTrue(f1.equals(f2));
	    }

	    @Test
	    void givenNegativeFeetValues_shouldReturnTrue() {
	        Feet f1 = new Feet(-3.0);
	        Feet f2 = new Feet(-3.0);
	        assertTrue(f1.equals(f2));
	    }

	    @Test
	    void givenZeroFeetValues_shouldReturnTrue() {
	        Feet f1 = new Feet(0.0);
	        Feet f2 = new Feet(0.0);
	        assertTrue(f1.equals(f2));
	    }
}
