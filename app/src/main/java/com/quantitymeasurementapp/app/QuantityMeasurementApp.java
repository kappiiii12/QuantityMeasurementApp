package com.quantitymeasurementapp.app;

/**
 * Hello world!
 */
public class QuantityMeasurementApp {
	
	//***************UC1*************************
	public static class Feet{
		private final double value;

		public Feet(double value) {
			super();
			this.value = value;
		}
		@Override
		public boolean equals(Object o) {
			if(o == null)
		    return false;
			
			if (this.getClass() != o.getClass()) return false;
			
			Feet feet = (Feet) o;
			
			return Double.compare(this.value , feet.value) == 0;
		}
	}
	//***************UC2*************************
	public static class Inches{
		private final double value;

		public Inches(double value) {
			super();
			this.value = value;
		}
		@Override
		public boolean equals(Object o) {
			if(o == null)
		    return false;
			
			if (this.getClass() != o.getClass()) return false;
			
			Inches inches = (Inches) o;
			
			return Double.compare(this.value , inches.value) == 0;
		}
	}
		
		public static boolean demonstrateFeetEquality(double d1 , double d2) {
			Feet f1 = new Feet(d1);
			Feet f2 = new Feet(d2);
			return f1.equals(f2);
		}
		
		public static boolean demonstrateInchesEquality(double d1 , double d2) {
			Inches i1 = new Inches(d1);
			Inches i2 = new Inches(d2);
			return i1.equals(i2);
		}
	
    public static void main(String[] args) {
    	   System.out.println(demonstrateFeetEquality(1.0, 1.0));   // true
    	     System.out.println(demonstrateFeetEquality(1.0, 2.0));   // false

    	     System.out.println(demonstrateInchesEquality(5.0, 5.0)); // true
    	     System.out.println(demonstrateInchesEquality(5.0, 7.0)); // false
        
    }
}
