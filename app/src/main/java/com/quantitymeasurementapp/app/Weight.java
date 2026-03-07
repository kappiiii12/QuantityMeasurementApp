package com.quantitymeasurementapp.app;

import java.util.Objects;

public class Weight {
	private final double value;
	  private final WeightUnit unit;
		
		public Weight(double value, WeightUnit unit) {
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
	    
	    public Weight convertTo(WeightUnit targetUnit) {
	    	  if (targetUnit == null) {
	              throw new IllegalArgumentException("Target unit cannot be null");
	          }

	          if (!Double.isFinite(this.value)) {
	              throw new IllegalArgumentException("Invalid value");
	          }

	    
	    	double res = this.convertToBaseUnit();
	    	res = res/targetUnit.getConversionFactor();
	    	return new Weight(res,targetUnit);
	    }
	    
	    public Weight add(Weight weight) {
	    	if(weight == null)
	    		throw new NullPointerException("Length cannot Null") ;
	    	
	    	weight = weight.convertTo(this.unit);
	    	double value = this.value + weight.value;
	    	return new Weight(value,this.unit);
	    }
	    
	    public Weight add(Weight weight,WeightUnit unit) {
	    	if(weight == null)
	    		throw new NullPointerException("Length cannot Null") ;
	    	weight = weight.convertTo(this.unit);
	    	double temp = this.value + weight.value;
	    	return new Weight(temp,this.unit).convertTo(unit);
	    }
		@Override
	    public boolean equals(Object o) {
			if(o == null)
				return false;
			if(this == o)
				return true;
			if(this.getClass() != o.getClass())
				return false;
			Weight temp = (Weight) o;
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

	  public WeightUnit getUnit() {
		return unit;
	  }
}
