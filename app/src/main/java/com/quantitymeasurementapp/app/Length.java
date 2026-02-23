package com.quantitymeasurementapp.app;

import java.util.Objects;

public class Length {
  private final double value;
  private final Unit unit;
	
	public Length(double value, Unit unit) {
	super();
	 if (unit == null) {
         throw new IllegalArgumentException("Unit cannot be null");
     }
	this.value = value;
	this.unit = unit;
}
	public enum Unit{
		FEET(12),
		INCHES(1),
		YARDS(36),
		CENTIMETERS(0.393701);
		
		private final double conversionFactor;
		 
		Unit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}
		
		public double convertToBase(double value) {
		return value * conversionFactor;
		}
	}	
		
    public double toInches() {
			return unit.convertToBase(value);
		}
    
    public Length convertTo(Unit targetUnit) {
    	  if (targetUnit == null) {
              throw new IllegalArgumentException("Target unit cannot be null");
          }

          if (!Double.isFinite(this.value)) {
              throw new IllegalArgumentException("Invalid value");
          }

    
    	double res = this.toInches();
    	res = res/targetUnit.conversionFactor;
    	return new Length(res,targetUnit);
    }
	@Override
    public boolean equals(Object o) {
		if(o == null)
			return false;
		if(this == o)
			return true;
		if(this.getClass() != o.getClass())
			return false;
		Length temp = (Length) o;
		return this.toInches() - temp.toInches() < 0.001;
			

	}
	@Override
    public int hashCode() {
		return Objects.hash(toInches());
	}
	
  @Override
  public String toString() {
	  return value+" "+unit;
  }
}
