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
    public static Length demonstrateLengthAddition(Length length1, Length length2, Length.Unit toUnit) {
    	if(length1 == null || length2 == null)
    		throw new NullPointerException("Length Cannot be Null");
    	return length1.add(length2,toUnit);
    	
    }
    public static void main(String[] args) {
    	// 1. FEET + INCHES -> FEET
        Length q1 = new Length(1.0, Length.Unit.FEET);
        Length q2 = new Length(12.0, Length.Unit.INCHES);
        System.out.println(q1.add(q2, Length.Unit.FEET)); 
        // Output: Quantity(2.000, FEET)

        // 2. FEET + INCHES -> INCHES
        System.out.println(q1.add(q2, Length.Unit.INCHES)); 
        // Output: Quantity(24.000, INCHES)

        // 3. FEET + INCHES -> YARDS
        System.out.println(q1.add(q2, Length.Unit.YARDS)); 
        // Output: Quantity(0.667, YARDS)

        // 4. YARDS + FEET -> YARDS
        Length q3 = new Length(1.0, Length.Unit.YARDS);
        Length q4 = new Length(3.0, Length.Unit.FEET);
        System.out.println(q3.add(q4, Length.Unit.YARDS)); 
        // Output: Quantity(2.000, YARDS)

        // 5. INCHES + YARDS -> FEET
        Length q5 = new Length(36.0, Length.Unit.INCHES);
        Length q6 = new Length(1.0, Length.Unit.YARDS);
        System.out.println(q5.add(q6, Length.Unit.FEET)); 
        // Output: Quantity(6.000, FEET)

        // 6. CM + INCHES -> CM
        Length q7 = new Length(2.54, Length.Unit.CENTIMETERS);
        Length q8 = new Length(1.0, Length.Unit.INCHES);
        System.out.println(q7.add(q8, Length.Unit.CENTIMETERS)); 
        // Output: Quantity(5.080, CENTIMETERS)

        // 7. FEET + 0 INCHES -> YARDS
        Length q9 = new Length(5.0, Length.Unit.FEET);
        Length q10 = new Length(0.0, Length.Unit.INCHES);
        System.out.println(q9.add(q10, Length.Unit.YARDS)); 
        // Output: Quantity(1.667, YARDS)

        // 8. FEET + NEGATIVE FEET -> INCHES
        Length q11 = new Length(5.0, Length.Unit.FEET);
        Length q12 = new Length(-2.0, Length.Unit.FEET);
        System.out.println(q11.add(q12, Length.Unit.INCHES)); 
        // Output: Quantity(36.000, INCHES)
    }
}
