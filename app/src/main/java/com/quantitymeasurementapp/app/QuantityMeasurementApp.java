package com.quantitymeasurementapp.app;

/**
 * Quantity Measurement Application
 */
public class QuantityMeasurementApp {

    // *********************************** LENGTH METHODS ****************************************

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return length1.equals(length2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null) throw new IllegalArgumentException("Units cannot be null");
        return new Length(value, sourceUnit).convertTo(targetUnit);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit toUnit) {
        if (length1 == null || length2 == null) throw new NullPointerException("Length cannot be null");
        return length1.add(length2, toUnit);
    }

    // *********************************** WEIGHT METHODS ****************************************

    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        if (weight1 == null) return weight2 == null;
        return weight1.equals(weight2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit sourceUnit, WeightUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null) throw new IllegalArgumentException("Units cannot be null");
        Weight temp = new Weight(value, sourceUnit);
        return temp.convertTo(targetUnit);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2) {
        if (weight1 == null || weight2 == null) throw new NullPointerException("Weight cannot be null");
        return weight1.add(weight2);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit toUnit) {
        if (weight1 == null || weight2 == null) throw new NullPointerException("Weight cannot be null");
        return weight1.add(weight2, toUnit);
    }

    // *********************************** MAIN METHOD *******************************************

    public static void main(String[] args) {
    	System.out.println("--- Weight Equality Comparisons ---");
    // 1kg == 1kg
    System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM))); 
    // 1kg == 1000g
    System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
    // 1kg == ~2.20462lb
    System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.20462, WeightUnit.POUND)));

    System.out.println("\n--- Weight Conversions ---");
    // 1kg to g
    System.out.println(new Weight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));
    // 2lb to kg
    System.out.println(new Weight(2.0, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM));

    System.out.println("\n--- Weight Addition (Implicit) ---");
    // 1kg + 1000g -> 2kg
    Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
    Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
    System.out.println(w1.add(w2));

    System.out.println("\n--- Weight Addition (Explicit) ---");
    // 2kg + 4lb -> Result in KG
    System.out.println(new Weight(2.0, WeightUnit.KILOGRAM).add(new Weight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM));
    }
}