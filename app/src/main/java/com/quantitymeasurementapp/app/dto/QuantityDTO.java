package com.quantitymeasurementapp.app.dto;

public class QuantityDTO {

    private double value;
    private String unit;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit  = unit;
    }

    public double getValue()        { return value; }
    public void setValue(double v)  { this.value = v; }
    public String getUnit()         { return unit; }
    public void setUnit(String u)   { this.unit = u; }
}