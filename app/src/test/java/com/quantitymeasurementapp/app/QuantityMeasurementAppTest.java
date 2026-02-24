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

    

	private static final double EPSILON = 0.0001;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(2.0, Length.Unit.FEET);
        Length result = l1.add(l2);
        
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.FEET, result.getUnit());
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        Length l1 = new Length(6.0, Length.Unit.INCHES);
        Length l2 = new Length(6.0, Length.Unit.INCHES);
        Length result = l1.add(l2);
        
        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        Length result = l1.add(l2);
        
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        Length l1 = new Length(12.0, Length.Unit.INCHES);
        Length l2 = new Length(1.0, Length.Unit.FEET);
        Length result = l1.add(l2);
        
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        Length l1 = new Length(1.0, Length.Unit.YARDS);
        Length l2 = new Length(3.0, Length.Unit.FEET);
        Length result = l1.add(l2);
        
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        Length l1 = new Length(2.54, Length.Unit.CENTIMETERS);
        Length l2 = new Length(1.0, Length.Unit.INCHES);
        Length result = l1.add(l2);
        
        // 1 inch is exactly 2.54 cm, so result should be 5.08
        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(Length.Unit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_Commutativity() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        
        Length res1 = l1.add(l2); // Result in FEET
        Length res2 = l2.add(l1); // Result in INCHES
        
        // To test logical equality, we compare their values in a base unit (e.g., inches)
        assertEquals(res1.toInches(), res2.toInches(), EPSILON);
    }

    @Test
    void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.Unit.FEET);
        Length l2 = new Length(0.0, Length.Unit.INCHES);
        Length result = l1.add(l2);
        
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, Length.Unit.FEET);
        Length l2 = new Length(-2.0, Length.Unit.FEET);
        Length result = l1.add(l2);
        
        assertEquals(3.0, result.getValue(), EPSILON);
    }
    @Test
    void testAddition_NullSecondOperand() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        
        assertThrows(NullPointerException.class, () -> {
            l1.add(null);
        });
    }

    @Test
    void testAddition_LargeValues() {
        Length l1 = new Length(1e6, Length.Unit.FEET);
        Length l2 = new Length(1e6, Length.Unit.FEET);
        Length result = l1.add(l2);
        
        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SmallValues() {
        Length l1 = new Length(0.001, Length.Unit.FEET);
        Length l2 = new Length(0.002, Length.Unit.FEET);
        Length result = l1.add(l2);
        
        assertEquals(0.003, result.getValue(), EPSILON);
    }
    //********************************UC7****************************
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        Length result = l1.add(l2, Length.Unit.FEET);
        
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        Length result = l1.add(l2, Length.Unit.INCHES);
        
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        Length result = l1.add(l2, Length.Unit.YARDS);
        
        // (1ft + 1ft) = 2/3 yards = ~0.6667
        assertEquals(0.6667, result.getValue(), EPSILON);
        assertEquals(Length.Unit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        Length l1 = new Length(1.0, Length.Unit.INCHES);
        Length l2 = new Length(1.0, Length.Unit.INCHES);
        Length result = l1.add(l2, Length.Unit.CENTIMETERS);
        
        // 2 inches = 5.08 cm
        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(Length.Unit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length l1 = new Length(2.0, Length.Unit.YARDS);
        Length l2 = new Length(3.0, Length.Unit.FEET);
        Length result = l1.add(l2, Length.Unit.YARDS);
        
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length l1 = new Length(2.0, Length.Unit.YARDS);
        Length l2 = new Length(3.0, Length.Unit.FEET);
        Length result = l1.add(l2, Length.Unit.FEET);
        
        // 2 yards (6ft) + 3ft = 9ft
        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(Length.Unit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        
        Length res1 = l1.add(l2, Length.Unit.YARDS);
        Length res2 = l2.add(l1, Length.Unit.YARDS);
        
        assertEquals(res1.getValue(), res2.getValue(), EPSILON);
        assertEquals(res1.getUnit(), res2.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        Length l1 = new Length(5.0, Length.Unit.FEET);
        Length l2 = new Length(0.0, Length.Unit.INCHES);
        Length result = l1.add(l2, Length.Unit.YARDS);
        
        // 5 feet = 1.6667 yards
        assertEquals(1.6667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length l1 = new Length(5.0, Length.Unit.FEET);
        Length l2 = new Length(-2.0, Length.Unit.FEET);
        Length result = l1.add(l2, Length.Unit.INCHES);
        
        // 3 feet = 36 inches
        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        
        assertThrows(IllegalArgumentException.class, () -> {
            l1.add(l2, null);
        });
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length l1 = new Length(1000.0, Length.Unit.FEET);
        Length l2 = new Length(500.0, Length.Unit.FEET);
        Length result = l1.add(l2, Length.Unit.INCHES);
        
        // 1500 feet * 12 = 18000 inches
        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length l1 = new Length(12.0, Length.Unit.INCHES);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        Length result = l1.add(l2, Length.Unit.YARDS);
        
        // 24 inches = 2/3 yards = 0.6667
        assertEquals(0.6667, result.getValue(), EPSILON);
    }
}

