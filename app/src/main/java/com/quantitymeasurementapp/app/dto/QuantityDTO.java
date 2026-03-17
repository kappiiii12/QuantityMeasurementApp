package com.quantitymeasurementapp.app.dto;

/**
 * This class is used to capture incoming JSON data from the API request.
 * It maps the JSON fields "value" and "unit" to Java variables.
 */
public class QuantityDTO {
    private double value;
    private String unit;

    // Default constructor for Jackson (JSON parser)
    public QuantityDTO() {}

    public QuantityDTO(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}