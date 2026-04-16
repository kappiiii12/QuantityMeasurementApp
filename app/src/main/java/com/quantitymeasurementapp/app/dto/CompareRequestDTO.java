package com.quantitymeasurementapp.app.dto;

/**
 * Used for comparison requests.
 * Example JSON:
 * { "value1": 1.0, "unit1": "FEET", "value2": 12.0, "unit2": "INCHES" }
 */
public class CompareRequestDTO {

    private double value1;
    private String unit1;
    private double value2;
    private String unit2;

    public double getValue1()       { return value1; }
    public void setValue1(double v) { this.value1 = v; }
    public String getUnit1()        { return unit1; }
    public void setUnit1(String u)  { this.unit1 = u; }
    public double getValue2()       { return value2; }
    public void setValue2(double v) { this.value2 = v; }
    public String getUnit2()        { return unit2; }
    public void setUnit2(String u)  { this.unit2 = u; }
}