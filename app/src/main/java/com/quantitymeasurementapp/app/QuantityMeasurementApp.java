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
    public static void main(String[] args) {
    	  // convert(1.0, FEET, INCHES) → 12.0
        System.out.println("1 FEET to INCHES: " +
                demonstrateLengthConversion(1.0, Length.Unit.FEET, Length.Unit.INCHES));

        // convert(3.0, YARDS, FEET) → 9.0
        System.out.println("3 YARDS to FEET: " +
                demonstrateLengthConversion(3.0, Length.Unit.YARDS, Length.Unit.FEET));

        // convert(36.0, INCHES, YARDS) → 1.0
        System.out.println("36 INCHES to YARDS: " +
                demonstrateLengthConversion(36.0, Length.Unit.INCHES, Length.Unit.YARDS));

        // convert(1.0, CENTIMETERS, INCHES) → ~0.393701
        System.out.println("1 CM to INCHES: " +
                demonstrateLengthConversion(1.0, Length.Unit.CENTIMETERS, Length.Unit.INCHES));

        // convert(0.0, FEET, INCHES) → 0.0
        System.out.println("0 FEET to INCHES: " +
                demonstrateLengthConversion(0.0, Length.Unit.FEET, Length.Unit.INCHES));
     
    }
}
