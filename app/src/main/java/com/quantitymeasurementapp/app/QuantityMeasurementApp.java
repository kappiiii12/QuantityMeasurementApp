package com.quantitymeasurementapp.app;

/**
 * Hello world!
 */
public class QuantityMeasurementApp {
	
//***********************************UC3*******************************************************

   //Generic Method to Compare Two Length
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

   // METHOD TO CREATR TWO LENGTHS AND COMPARE THEM
    public static boolean demonstrateLengthComparison(
            double value1, Length.Unit unit1,
            double value2, Length.Unit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return length1.equals(length2);
    }
 
   // METHOD FOR LENGTH CONVERSION 
    public static Length demonstrateLengthConversion(double value,Length.Unit sourceUnit, Length.Unit targetUnit) {
    	if(sourceUnit == null) {
    		throw new IllegalArgumentException("Length cannot be null");
    	}
    	
        if (targetUnit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
    	Length temp = new Length(value,sourceUnit);
    	return temp.convertTo(targetUnit);
    }
    public static Length demonstrateLengthConversion(Length length,Length.Unit targetUnit) {
    	if(length == null) {
    		throw new IllegalArgumentException("Length cannot be null");
    	}
    	
        if (targetUnit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
    	return length.convertTo(targetUnit);
    }
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
    	if(length1==null || length2==null)
    		throw new NullPointerException("length cannot be null");
    	return length1.add(length2);
    }
    public static void main(String[] args) {
    	// 1. FEET + INCHES -> FEET
        Length l1 = new Length(1.0, Length.Unit.FEET);
        Length l2 = new Length(12.0, Length.Unit.INCHES);
        System.out.println("Result 1: " + l1.add(l2)); // Output: 2.0 FEET

        // 2. INCHES + FEET -> INCHES
        Length l3 = new Length(12.0, Length.Unit.INCHES);
        Length l4 = new Length(1.0, Length.Unit.FEET);
        System.out.println("Result 2: " + l3.add(l4)); // Output: 24.0 INCHES

        // 3. YARDS + FEET -> YARDS
        Length l5 = new Length(1.0, Length.Unit.YARDS);
        Length l6 = new Length(3.0, Length.Unit.FEET);
        System.out.println("Result 3: " + l5.add(l6)); // Output: 2.0 YARDS

        // 4. INCHES + YARDS -> INCHES
        Length l7 = new Length(36.0, Length.Unit.INCHES);
        Length l8 = new Length(1.0, Length.Unit.YARDS);
        System.out.println("Result 4: " + l7.add(l8)); // Output: 72.0 INCHES

        // 5. CENTIMETERS + INCHES -> CENTIMETERS
        Length l9 = new Length(2.54, Length.Unit.CENTIMETERS);
        Length l10 = new Length(1.0, Length.Unit.INCHES);
        System.out.println("Result 5: " + l9.add(l10)); // Output: ~5.08 CENTIMETERS

        // 6. Handling 0.0 value
        Length l11 = new Length(5.0, Length.Unit.FEET);
        Length l12 = new Length(0.0, Length.Unit.INCHES);
        System.out.println("Result 6: " + l11.add(l12)); // Output: 5.0 FEET

        // 7. Handling negative value (Subtraction)
        Length l13 = new Length(5.0, Length.Unit.FEET);
        Length l14 = new Length(-2.0, Length.Unit.FEET);
        System.out.println("Result 7: " + l13.add(l14)); // Output: 3.0 FEET
    }
}
