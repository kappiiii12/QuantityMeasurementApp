package com.quantitymeasurementapp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;



/**
 * Unit test for simple
 */
public class QuantityMeasurementAppTest {

    


    @Test
    void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        Length l1 = new Length(36.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    /* =========================================================
       CENTIMETER TESTS
       ========================================================= */

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    /* =========================================================
       TRANSITIVE PROPERTY TEST
       ========================================================= */

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    /* =========================================================
       NULL UNIT TESTS
       ========================================================= */

    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    /* =========================================================
       REFLEXIVE PROPERTY
       ========================================================= */

    @Test
    void testEquality_YardSameReference() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        Length cm = new Length(2.0, LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }

    /* =========================================================
       NULL COMPARISON TESTS
       ========================================================= */

    @Test
    void testEquality_YardNullComparison() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    /* =========================================================
       COMPLEX MULTI-UNIT SCENARIO
       ========================================================= */

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(6.0, LengthUnit.FEET);
        Length inches = new Length(72.0, LengthUnit.INCHES);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
    
    /* =========================================================
    UC5: CONVERSION TESTS
    ========================================================= */

 @Test
 void testConversion_FeetToInches() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

     assertEquals(new Length(12.0, LengthUnit.INCHES), result);
 }

 @Test
 void testConversion_InchesToFeet() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(24.0, LengthUnit.INCHES, LengthUnit.FEET);

     assertEquals(new Length(2.0, LengthUnit.FEET), result);
 }

 @Test
 void testConversion_YardsToInches() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.INCHES);

     assertEquals(new Length(36.0, LengthUnit.INCHES), result);
 }

 @Test
 void testConversion_InchesToYards() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(72.0, LengthUnit.INCHES, LengthUnit.YARDS);

     assertEquals(new Length(2.0, LengthUnit.YARDS), result);
 }

 @Test
 void testConversion_CentimetersToInches() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);

     assertEquals(new Length(1.0, LengthUnit.INCHES), result);
 }

 @Test
 void testConversion_FeetToYards() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(6.0, LengthUnit.FEET, LengthUnit.YARDS);

     assertEquals(new Length(2.0, LengthUnit.YARDS), result);
 }
 
 /* =========================================================
 UC5: Edge Case Tests
 ========================================================= */
 
 @Test
 void testConversion_ZeroValue() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

     assertEquals(new Length(0.0, LengthUnit.INCHES), result);
 }

 @Test
 void testConversion_NegativeValue() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCHES);

     assertEquals(new Length(-12.0, LengthUnit.INCHES), result);
 }

 @Test
 void testConversion_SameUnit() {
     Length result = QuantityMeasurementApp
             .demonstrateLengthConversion(5.0, LengthUnit.FEET, LengthUnit.FEET);

     assertEquals(new Length(5.0, LengthUnit.FEET), result);
 }
 
 
 
 /* =========================================================
 UC5: Exception Tests
 ========================================================= */
 
 @Test
 void testConversion_InvalidUnit_Throws() {
     assertThrows(IllegalArgumentException.class,
             () -> QuantityMeasurementApp
                     .demonstrateLengthConversion(1.0, null, LengthUnit.FEET));
 }

 @Test
 void testConversion_NaN_Throws() {
     assertThrows(IllegalArgumentException.class,
             () -> QuantityMeasurementApp
                     .demonstrateLengthConversion(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
 }

 @Test
 void testConversion_Infinite_Throws() {
     assertThrows(IllegalArgumentException.class,
             () -> QuantityMeasurementApp
                     .demonstrateLengthConversion(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES));
 }
 
 /* =========================================================
 LENGTH EQUALITY TESTS
 ========================================================= */


@Test
void testEquality_CentimetersToInches_EquivalentValue() {
  Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
  Length l2 = new Length(0.393701, LengthUnit.INCHES);
  assertTrue(l1.equals(l2));
}



@Test
void testAddition_ExplicitTargetUnit_Centimeters() {
  Length result = QuantityMeasurementApp.demonstrateLengthAddition(
          new Length(1.0, LengthUnit.INCHES),
          new Length(1.0, LengthUnit.INCHES),
          LengthUnit.CENTIMETERS);
  Length expected = new Length(5.08, LengthUnit.CENTIMETERS);
  assertTrue(result.equals(expected));
}

/* =========================================================
 WEIGHT EQUALITY TESTS
 ========================================================= */

@Test
void testWeightEquality_KilogramToGram_EquivalentValue() {
  Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
  Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
  assertTrue(w1.equals(w2));
}

@Test
void testWeightEquality_KilogramToPound_EquivalentValue() {
  Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
  Weight w2 = new Weight(2.20462, WeightUnit.POUND);
  assertTrue(w1.equals(w2));
}

/* =========================================================
 WEIGHT CONVERSION TESTS
 ========================================================= */

@Test
void testWeightConversion_KilogramToGram() {
  Weight result = QuantityMeasurementApp
          .demonstrateWeightConversion(1.5, WeightUnit.KILOGRAM, WeightUnit.GRAM);
  assertEquals(new Weight(1500.0, WeightUnit.GRAM), result);
}

/* =========================================================
 WEIGHT ADDITION TESTS
 ========================================================= */

@Test
void testWeightAddition_KilogramPlusGram_ImplicitResult() {
  Weight result = QuantityMeasurementApp.demonstrateWeightAddition(
          new Weight(1.0, WeightUnit.KILOGRAM),
          new Weight(500.0, WeightUnit.GRAM));
  assertEquals(new Weight(1.5, WeightUnit.KILOGRAM), result);
}

@Test
void testWeightAddition_ExplicitTargetUnit_Grams() {
  Weight result = QuantityMeasurementApp.demonstrateWeightAddition(
          new Weight(1.0, WeightUnit.KILOGRAM),
          new Weight(2.0, WeightUnit.POUND),
          WeightUnit.GRAM);
  
  // Validation using a delta for floating point precision
  double expectedGrams = 1000.0 + (2.0 / 0.00220462); 
  assertEquals(expectedGrams, result.getValue(), 0.01);
}

/* =========================================================
 EXCEPTION AND EDGE CASE TESTS
 ========================================================= */

@Test
void testConversion_NullUnit_Throws() {
  assertThrows(IllegalArgumentException.class,
          () -> QuantityMeasurementApp
                  .demonstrateLengthConversion(1.0, null, LengthUnit.FEET));
}

@Test
void testWeightAddition_NullOperand_Throws() {
  assertThrows(NullPointerException.class,
          () -> QuantityMeasurementApp.demonstrateWeightAddition(null, new Weight(1.0, WeightUnit.GRAM)));
}
                                                                                                                                                                                           
}                                                                                                                                                                                                           
                                                                                                                                                                                                                                                                           