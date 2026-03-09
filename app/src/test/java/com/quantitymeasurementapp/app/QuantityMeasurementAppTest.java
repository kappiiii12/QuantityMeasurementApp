package com.quantitymeasurementapp.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

	@Test
	void testIMeasurableInterface_LengthUnitImplementation() {
		IMeasurable unit = LengthUnit.FEET;
		assertEquals(12.0, unit.getConversionFactor());
		assertEquals("FEET", unit.getUnitName());
		assertEquals(12.0, unit.convertToBaseUnit(1.0));
	}

	@Test
	void testIMeasurableInterface_WeightUnitImplementation() {
		IMeasurable unit = WeightUnit.KILOGRAM;
		assertEquals(1000.0, unit.getConversionFactor());
		assertEquals("KILOGRAM", unit.getUnitName());
		assertEquals(1000.0, unit.convertToBaseUnit(1.0));
	}

	@Test
	void testIMeasurableInterface_ConsistentBehavior() {
		IMeasurable length = LengthUnit.INCHES;
		IMeasurable weight = WeightUnit.GRAM;

		assertEquals(1.0, length.getConversionFactor());
		assertEquals(1.0, weight.getConversionFactor());
	}

	@Test
	void testGenericQuantity_LengthOperations_Equality() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
		assertTrue(q1.equals(q2));
	}

	@Test
	void testGenericQuantity_WeightOperations_Equality() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
		assertTrue(q1.equals(q2));
	}

	@Test
	void testCrossCategoryPrevention_LengthVsWeight() {
		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(length.equals(weight));
	}

	@Test
	void testGenericQuantity_LengthOperations_Conversion() {
		Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

		assertEquals(12.0, result.getValue());
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	@Test
	void testGenericQuantity_WeightOperations_Conversion() {
		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);
		
		assertEquals(1000.0, result.getValue());
		assertEquals(WeightUnit.GRAM, result.getUnit());
	}

	@Test
	void testGenericQuantity_LengthOperations_Addition() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

		assertEquals(2.0, result.getValue());
		assertEquals(LengthUnit.FEET, result.getUnit());
	}

	@Test
	void testGenericQuantity_WeightOperations_Addition() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

		assertEquals(2.0, result.getValue());
		assertEquals(WeightUnit.KILOGRAM, result.getUnit());
	}

	@Test
	void testGenericQuantity_ConstructorValidation_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
	}

	@Test
	void testGenericQuantity_ConstructorValidation_InvalidValue() {
		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
	}

	@Test
	void testHashCode_GenericQuantity_Consistency() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
		assertEquals(q1, q2);
		assertEquals(q1.hashCode(), q2.hashCode());
	}

	@Test
	void testEquals_GenericQuantity_ContractPreservation() {
		Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
	}

	@Test
	void testImmutability_GenericQuantity() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> q2 = q1.convertTo(LengthUnit.INCHES);

		assertNotSame(q1, q2);
	}

	@Test
	void testTypeWildcard_FlexibleSignatures() {
		Quantity<?> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<?> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertNotNull(q1);
		assertNotNull(q2);
	}

	@Test
	void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertDoesNotThrow(() -> QuantityMeasurementApp.demonstrateEquality(q1, q2));
	}

	@Test
	void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertDoesNotThrow(() -> QuantityMeasurementApp.demonstrateConversion(q, WeightUnit.GRAM));
	}

	@Test
	void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertDoesNotThrow(() -> QuantityMeasurementApp.demonstrateAddition(q1, q2, WeightUnit.KILOGRAM));
	}
	
	@Test
	void testVolumeEquality_LitreToLitre_Same() {
	    assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
	            .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
	}

	@Test
	void testVolumeEquality_LitreToMillilitre() {
	    assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
	            .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
	}

	@Test
	void testVolumeEquality_MillilitreToLitre() {
	    assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	            .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
	}

	@Test
	void testVolumeEquality_GallonToLitre() {
	    assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
	            .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
	}

	@Test
	void testVolumeEquality_WithNull() {
	    assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
	}

	@Test
	void testVolumeEquality_SameReference() {
	    Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
	    assertTrue(v.equals(v));
	}

	@Test
	void testVolumeEquality_NullUnit() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Quantity<>(1.0, null));
	}
	
	// === === conversion test === ===
	
	@Test
	void testVolumeConversion_LitreToMillilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .convertTo(VolumeUnit.MILLILITRE);

	    assertEquals(1000.0, result.getValue());
	}

	@Test
	void testVolumeConversion_MillilitreToLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
	                    .convertTo(VolumeUnit.LITRE);

	    assertEquals(1.0, result.getValue());
	}

	@Test
	void testVolumeConversion_GallonToLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.GALLON)
	                    .convertTo(VolumeUnit.LITRE);

	    assertEquals(3.78541, result.getValue(), 0.01);
	}

	@Test
	void testVolumeConversion_LitreToGallon() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(3.78541, VolumeUnit.LITRE)
	                    .convertTo(VolumeUnit.GALLON);

	    assertEquals(1.0, result.getValue(), 0.01);
	}

	@Test
	void testVolumeConversion_SameUnit() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .convertTo(VolumeUnit.LITRE);

	    assertEquals(5.0, result.getValue());
	}
	
	// === === addition test === ===
	
	@Test
	void testVolumeAddition_SameUnit_Litre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(2.0, VolumeUnit.LITRE));

	    assertEquals(3.0, result.getValue());
	}

	@Test
	void testVolumeAddition_CrossUnit_LitreMillilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

	    assertEquals(2.0, result.getValue());
	}

	@Test
	void testVolumeAddition_CrossUnit_GallonLitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.GALLON)
	                    .add(new Quantity<>(3.78541, VolumeUnit.LITRE));

	    assertEquals(2.0, result.getValue(), 0.01);
	}
	
	// === === target unit test === === 
	
	@Test
	void testVolumeAddition_Target_Litre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE);

	    assertEquals(2.0, result.getValue());
	}

	@Test
	void testVolumeAddition_Target_Millilitre() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);

	    assertEquals(2000.0, result.getValue());
	}

	@Test
	void testVolumeAddition_Target_Gallon() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(3.78541, VolumeUnit.LITRE)
	                    .add(new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON);

	    assertEquals(2.0, result.getValue(), 0.01);
	}
	
	// === === === edgecase === === === 
	
	@Test
	void testVolumeAddition_WithZero() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

	    assertEquals(5.0, result.getValue());
	}

	@Test
	void testVolumeAddition_Negative() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(5.0, VolumeUnit.LITRE)
	                    .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

	    assertEquals(3.0, result.getValue());
	}

	@Test
	void testVolumeAddition_LargeValues() {
	    Quantity<VolumeUnit> result =
	            new Quantity<>(1e6, VolumeUnit.LITRE)
	                    .add(new Quantity<>(1e6, VolumeUnit.LITRE));

	    assertEquals(2e6, result.getValue());
	}
	
	// === === Enum test === === 
	
	@Test
	void testVolumeUnit_LitreFactor() {
	    assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor());
	}

	@Test
	void testVolumeUnit_MillilitreFactor() {
	    assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor());
	}

	@Test
	void testVolumeUnit_GallonFactor() {
	    assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor());
	}
	// === === Subtraction Tests === ===

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(5.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
        assertEquals(7.0, result.getValue());
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        // 10ft - 6in = 9.5ft
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(9.5, result.getValue());
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        // 120in - 5ft = 60in
        Quantity<LengthUnit> result = new Quantity<>(120.0, LengthUnit.INCHES)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(60.0, result.getValue());
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        // 10ft - 6in target Inches -> 120in - 6in = 114in
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(114.0, result.getValue());
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(-5.0, result.getValue());
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(120.0, LengthUnit.INCHES));
        assertEquals(0.0, result.getValue());
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        // 5 - (-2) = 7
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(-2.0, LengthUnit.FEET));
        assertEquals(7.0, result.getValue());
    }

    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
    }

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> length.subtract(weight));
    }

    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                .subtract(new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(7.0, result.getValue());
    }
    
    
 // === === Division Tests === ===

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        // Division returns a double (dimensionless ratio)
        double ratio = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(5.0, ratio);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        // 24in / 2ft = 1.0
        double ratio = new Quantity<>(24.0, LengthUnit.INCHES)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(1.0, ratio);
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    void testDivision_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        
        assertEquals(2.0, a.divide(b));
        assertEquals(0.5, b.divide(a));
    }

    @Test
    void testDivision_WithSmallRatio() {
        double ratio = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM));
        assertEquals(1e-6, ratio, 1e-9);
    }

    @Test
    void testSubtractionAndDivision_Integration() {
        // (10ft - 5ft) / 2.5ft = 2.0
        Quantity<LengthUnit> sub = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        double result = sub.divide(new Quantity<>(2.5, LengthUnit.FEET));
        
        assertEquals(2.0, result);
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        
        q1.subtract(q2);
        
        // Original value should remain 10.0
        assertEquals(10.0, q1.getValue());
    }
}