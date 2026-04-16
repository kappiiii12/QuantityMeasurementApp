package com.quantitymeasurementapp.app.dto;

/**
 * Used for unit conversion.
 * Example JSON:
 * { "value": 1.0, "fromUnit": "FEET", "toUnit": "INCHES" }
 */
public class ConversionRequestDTO {

    private double value;
    private String fromUnit;
    private String toUnit;

    public double getValue()            { return value; }
    public void setValue(double v)      { this.value = v; }
    public String getFromUnit()         { return fromUnit; }
    public void setFromUnit(String u)   { this.fromUnit = u; }
    public String getToUnit()           { return toUnit; }
    public void setToUnit(String u)     { this.toUnit = u; }
}