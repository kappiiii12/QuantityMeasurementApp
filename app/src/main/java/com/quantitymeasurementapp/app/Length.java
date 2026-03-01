package com.quantitymeasurementapp.app;

import java.util.Objects;

public class Length {
  private final double value;
  private final LengthUnit unit;
	
	public Length(double value, LengthUnit unit) {
	super();
	 if (unit == null) {
         throw new IllegalArgumentException("Unit cannot be null");
     }
	this.value = value;
	this.unit = unit;
}
	
		
    public double convertToBaseUnit() {
			return unit.convertToBase(value);
		}
    
    public Length convertTo(LengthUnit targetUnit) {
    	  if (targetUnit == null) {
              throw new IllegalArgumentException("Target unit cannot be null");
          }

          if (!Double.isFinite(this.value)) {
              throw new IllegalArgumentException("Invalid value");
          }

    
    	double res = this.convertToBaseUnit();
    	res = res/targetUnit.getConversionFactor();
    	return new Length(res,targetUnit);
    }
    
    public Length add(Length length) {
    	if(length == null)
    		throw new NullPointerException("Length cannot Null") ;
    	
    	length = length.convertTo(this.unit);
    	double value = this.value + length.value;
    	return new Length(value,this.unit);
    }
    
    public Length add(Length length,LengthUnit unit) {
    	if(length == null)
    		throw new NullPointerException("Length cannot Null") ;
    	length = length.convertTo(this.unit);
    	double temp = this.value + length.value;
    	return new Length(temp,this.unit).convertTo(unit);
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
		return this.convertToBaseUnit() - temp.convertToBaseUnit() < 0.001;
			

	}
	@Override
    public int hashCode() {
		return Objects.hash(convertToBaseUnit());
	}
	
  @Override
  public String toString() {
	  return value+" "+unit;
  }

  public double getValue() {
	return value;
  }

  public LengthUnit getUnit() {
	return unit;
  }
  
}
