package com.quantitymeasurementapp.app.dto;

public class ConversionRequestDTO {
    private double value;
    private String fromUnit;
    private String toUnit;

    // Getters and Setters
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public String getFromUnit() { return fromUnit; }
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }
    public String getToUnit() { return toUnit; }
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }
}