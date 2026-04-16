package com.quantitymeasurementapp.app.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "measurements")
public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-9;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double value;

    private String unitName;

    @Transient
    private U unit;

    // Required by JPA
    protected Quantity() {}

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null.");
        this.value = value;
        this.unit = unit;
        this.unitName = unit.getUnitName();
    }

    // ─── Getters & Setters ───────────────────────────────────────────────────

    public Long getId()               { return id; }
    public void setId(Long id)        { this.id = id; }
    public double getValue()          { return value; }
    public U getUnit()                { return unit; }
    public String getUnitName()       { return unitName; }
    public void setUnitName(String u) { this.unitName = u; }

    // ─── Core Operations ─────────────────────────────────────────────────────

    /** Convert this quantity to the given target unit. */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null.");
        requireSameDomain(targetUnit);
        double converted = targetUnit.convertFromBaseUnit(this.toBase());
        return new Quantity<>(round(converted), targetUnit);
    }

    /** Add another quantity; result is expressed in THIS quantity's unit. */
    public Quantity<U> add(Quantity<U> other) {
        validateOperand(other, "addition");
        this.unit.validateArithmeticSupport("addition");
        return fromBase(toBase() + other.toBase(), this.unit);
    }

    /** Subtract another quantity; result is expressed in THIS quantity's unit. */
    public Quantity<U> subtract(Quantity<U> other) {
        validateOperand(other, "subtraction");
        this.unit.validateArithmeticSupport("subtraction");
        return fromBase(toBase() - other.toBase(), this.unit);
    }

    /**
     * Divide this quantity by another (same domain).
     * Returns a dimensionless ratio.
     */
    public double divide(Quantity<U> other) {
        validateOperand(other, "division");
        if (Math.abs(other.toBase()) < EPSILON)
            throw new ArithmeticException("Division by zero is not allowed.");
        return round(toBase() / other.toBase());
    }

    /**
     * Compare this quantity with another (same domain).
     * Returns negative, zero, or positive — like Comparable.
     */
    public int compareTo(Quantity<U> other) {
        validateOperand(other, "comparison");
        return Double.compare(this.toBase(), other.toBase());
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private static <U extends IMeasurable> Quantity<U> fromBase(double baseValue, U unit) {
        return new Quantity<>(round(baseValue / unit.getConversionFactor()), unit);
    }

    private void validateOperand(Quantity<U> other, String operation) {
        if (other == null)
            throw new IllegalArgumentException("Operand for " + operation + " cannot be null.");
        requireSameDomain(other.unit);
    }

    private void requireSameDomain(U other) {
        if (this.unit.getClass() != other.getClass())
            throw new IllegalArgumentException(
                "Units must belong to the same measurement domain.");
    }

    private static double round(double value) {
        return Math.round(value * 1_000_000.0) / 1_000_000.0;
    }

    // ─── Object overrides ────────────────────────────────────────────────────

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> that)) return false;
        if (this.unit.getClass() != that.unit.getClass()) return false;
        return Math.abs(this.toBase() - that.unit.convertToBaseUnit(that.value)) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(toBase()));
    }

    @Override
    public String toString() {
        return value + " " + unitName;
    }
}