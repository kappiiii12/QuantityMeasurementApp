package com.quantitymeasurementapp.app;

/**
 * Hello world!
 */
public class QuantityMeasurementApp {
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
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
    }
}
