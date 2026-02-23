package com.quantitymeasurementapp.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.app.Length.Unit;
import com.quantitymeasurementapp.app.QuantityMeasurementApp.Feet;
import com.quantitymeasurementapp.app.QuantityMeasurementApp.Inches;

/**
 * Unit test for simple
 */
public class QuantityMeasurementAppTest {

    
	  @Test
	    void givenSameFeetValues_shouldReturnTrue() {
	        Length l1 = new Length(1, Unit.FEET);
	        Length l2 = new Length(1, Unit.FEET);

	        assertTrue(l1.equals(l2));
	    }

	    @Test
	    void givenSameInchValues_shouldReturnTrue() {
	        Length l1 = new Length(12, Unit.INCHES);
	        Length l2 = new Length(12, Unit.INCHES);

	        assertTrue(l1.equals(l2));
	    }

	    @Test
	    void givenDifferentFeetValues_shouldReturnFalse() {
	        Length l1 = new Length(1, Unit.FEET);
	        Length l2 = new Length(2, Unit.FEET);

	        assertFalse(l1.equals(l2));
	    }

	    /* =========================
	       CROSS UNIT TESTS
	       ========================= */

	    @Test
	    void givenOneFootAndTwelveInches_shouldReturnTrue() {
	        Length l1 = new Length(1, Unit.FEET);
	        Length l2 = new Length(12, Unit.INCHES);

	        assertTrue(l1.equals(l2));
	    }

	    @Test
	    void givenTwoFeetAndTwentyFourInches_shouldReturnTrue() {
	        Length l1 = new Length(2, Unit.FEET);
	        Length l2 = new Length(24, Unit.INCHES);

	        assertTrue(l1.equals(l2));
	    }

	    @Test
	    void givenOneFootAndTenInches_shouldReturnFalse() {
	        Length l1 = new Length(1, Unit.FEET);
	        Length l2 = new Length(10, Unit.INCHES);

	        assertFalse(l1.equals(l2));
	    }

	    @Test
	    void givenSymmetricComparison_shouldReturnTrue() {
	        Length l1 = new Length(12, Unit.INCHES);
	        Length l2 = new Length(1, Unit.FEET);

	        assertTrue(l1.equals(l2));
	    }

	    /* =========================
	       EQUALS CONTRACT TESTS
	       ========================= */

	    @Test
	    void givenSameReference_shouldReturnTrue() {
	        Length l1 = new Length(1, Unit.FEET);
	        assertTrue(l1.equals(l1));
	    }

	    @Test
	    void givenNullComparison_shouldReturnFalse() {
	        Length l1 = new Length(1, Unit.FEET);
	        assertFalse(l1.equals(null));
	    }

	    @Test
	    void givenDifferentObjectType_shouldReturnFalse() {
	        Length l1 = new Length(1, Unit.FEET);
	        assertFalse(l1.equals("1"));
	    }

	    /* =========================
	       STATIC METHOD TEST
	       ========================= */

	    @Test
	    void givenCompareMethod_shouldReturnTrue() {
	        assertTrue(
	            QuantityMeasurementApp.compare(1, Unit.FEET, 12, Unit.INCHES)
	        );
	    }

	    @Test
	    void givenCompareMethodWithInvalidValues_shouldReturnFalse() {
	        assertFalse(
	            QuantityMeasurementApp.compare(1, Unit.FEET, 10, Unit.INCHES)
	        );
	}
}

