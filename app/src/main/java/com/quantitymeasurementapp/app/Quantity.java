package com.quantitymeasurementapp.app;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
  private final double value;
  private final U unit;
  
  public Quantity(double value, U unit) {
	  if (unit == null) {
          throw new IllegalArgumentException("Unit cannot be null.");
      }
    	  if(!Double.isFinite(value)) {
      	throw new IllegalArgumentException("value cannot be Infinite.");
      }
	
	this.value = value;
	this.unit = unit;
  }
  private double toBase() {
		return unit.convertToBaseUnit(value);
	}
	
	public double getValue() {
	return value;
}
  public U getUnit() {
	return unit;
  }
  public static double getEpsilon() {
	return EPSILON;
  }

	private static final double EPSILON = 1e-9;
	
	public Quantity<U> convertTo(U targetUnit){
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}else if(!sameDomain(this.unit, targetUnit)) {
			throw new IllegalArgumentException("Invalid target unit");
		}
		// convert to base unit
		double baseValue = this.toBase();
		// convert to target unit
		double convertedValue = baseValue / targetUnit.getConversionFactor();
		System.out.print(convertedValue);
		return new Quantity<U>(round(convertedValue), targetUnit);
	}
	
	public Quantity<U> add(Quantity<U> thatQuantity){
		if(thatQuantity == null) {
			throw new IllegalArgumentException("Quantity can not be null");
		}else if(!sameDomain(this.unit, thatQuantity.unit)) {
			throw new IllegalArgumentException("Quantity's are not of same domain.");
		}
		
		double thisBase = (toBase() + thatQuantity.toBase())/this.unit.getConversionFactor();
		
		return new Quantity<>(round(thisBase), this.unit);
	}
	public Quantity<U> subtract(Quantity<U> quantity){
		if(quantity == null)
			throw new IllegalArgumentException("Quantity  can not be null");
		else if(!sameDomain(this.unit, quantity.unit)) 
			throw new IllegalArgumentException("Quantity's are not of same domain.");
		double value = (this.toBase() - quantity.toBase())/this.unit.getConversionFactor();
		
		return new Quantity<>(round(value),this.unit);
	}
	public double divide(Quantity<U> quantity) {
	    // 1. Null Check
	    if (quantity == null) {
	        throw new IllegalArgumentException("Target quantity cannot be null.");
	    }

	    // 2. Division by Zero Check
	    if (quantity.getValue() == 0) {
	        throw new ArithmeticException("Division by zero is not allowed.");
	    }

	    // 3. Domain/Category Check (Assuming sameDomain is your helper method)
	    if (!sameDomain(this.unit, quantity.unit)) {
	        throw new IllegalArgumentException("Quantities are not from the same measurement domain.");
	    }

	    // 4. Calculation: (Value1 * Factor1) / (Value2 * Factor2)
	    // This provides the absolute ratio between the two measurements.
	    return this.toBase() / quantity.toBase();
	}
	
	private boolean sameDomain(U unit1, U unit2) {
		return unit1.getClass() == unit2.getClass();
	}
	
	private double round(double value) {
      return Math.round(value * 100.0) / 100.0;
  }
	
	public Quantity<U> add(Quantity<U> thatQuantity, U targetUnit){
		if(thatQuantity == null || unit == null) {
			throw new IllegalArgumentException("Quantity and Targetunit can not be null");
		}
		return this.add(thatQuantity).convertTo(targetUnit);
	}
	public Quantity<U> subtract(Quantity<U> thatQuantity, U targetUnit){
		if(thatQuantity == null || unit == null) {
			throw new IllegalArgumentException("Quantity and Targetunit can not be null");
		}
		return this.subtract(thatQuantity).convertTo(targetUnit);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Quantity<?>)) {
			return false; 
		}
		Quantity<?> that = (Quantity<?>) obj;
		if(this.unit.getClass() != that.unit.getClass()) {
			return false;
		}
		return Double.compare( this.unit.convertToBaseUnit(value), that.unit.convertToBaseUnit(that.value)) < EPSILON;
	}
	
	@Override
  public int hashCode() {
      return Objects.hash(toBase());
  }
	
	@Override
  public String toString() {
      return value + " " + unit;
  }
	
  
}
