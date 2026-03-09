package com.quantitymeasurementapp.app;

public class QuantityMeasurementApp {

	public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {

		System.out.println("Input: " + q1 + " equals " + q2 + " → Output: " + q1.equals(q2));
	}

	public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {

		System.out.println("Input: " + q + " convertTo " + targetUnit + " → Output: " + q.convertTo(targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {

		System.out.println("Input: " + q1 + " add " + q2 + " → Output: " + q1.add(q2, targetUnit));
	}
	
	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {
		System.out.println("Input: " + q1 + " add " + q2 + " → Output: " + q1.add(q2));
	}
	public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
	    System.out.println("Input: " + q1 + " subtract " + q2 + " (Target: " + targetUnit + ") → Output: " + q1.subtract(q2, targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2) {
	    System.out.println("Input: " + q1 + " subtract " + q2 + " → Output: " + q1.subtract(q2));
	}
	
	public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
	    System.out.println("Input: " + q1 + " divide " + q2 + " → Output: " + q1.divide(q2));
	}
	public static void main(String[] args) {

		// --- Subtraction with Implicit Target Unit ---
        System.out.println("### Subtraction with Implicit Target Unit ###");
        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES));
        demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM), new Quantity<>(5000.0, WeightUnit.GRAM));
        demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        // --- Subtraction with Explicit Target Unit ---
        System.out.println("\n### Subtraction with Explicit Target Unit ###");
        // Ensure the third argument matches the specific Unit type of the first two
        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);
        demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM), new Quantity<>(5000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);

        // --- Edge Cases: Negative and Zero ---
        System.out.println("\n### Subtraction: Negative and Zero ###");
        demonstrateSubtraction(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET));
        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(120.0, LengthUnit.INCHES));

        // --- Division Operations ---
        System.out.println("\n### Division Operations ###");
        demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET));
        demonstrateDivision(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.FEET));
        demonstrateDivision(new Quantity<>(2000.0, WeightUnit.GRAM), new Quantity<>(1.0, WeightUnit.KILOGRAM));

        // --- Error Cases ---
//        System.out.println("\n### Error Cases ###");
//        handleError(() -> demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), null), "Subtracting Null");
//        handleError(() -> demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(0.0, LengthUnit.FEET)), "Division by Zero");
//        
//        // Note: Cross-category subtraction usually fails at compile-time if your 
//        // demonstrateSubtraction method uses strict generics (e.g., <T extends Enum<T> & Unit>)
//        handleError(() -> demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(5.0, WeightUnit.KILOGRAM)), "Cross-Category Subtraction");
      

	}
}