package com.quantitymeasurementapp.app.service;

import com.quantitymeasurementapp.app.dto.*;
import com.quantitymeasurementapp.app.entity.IMeasurable;
import com.quantitymeasurementapp.app.entity.Quantity;
import com.quantitymeasurementapp.app.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository repository;
    private final UnitFactory unitFactory;

    public MeasurementService(MeasurementRepository repository, UnitFactory unitFactory) {
        this.repository  = repository;
        this.unitFactory = unitFactory;
    }

    // ─── Save / Fetch ─────────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    public Quantity<?> save(QuantityDTO dto) {
        IMeasurable unit = unitFactory.getUnit(dto.getUnit());
        return repository.save(new Quantity<>(dto.getValue(), unit));
    }

    public List<Quantity<?>> getAll() {
        return repository.findAll();
    }

    // ─── Convert ─────────────────────────────────────────────────────────────

    public QuantityDTO convert(ConversionRequestDTO req) {
        IMeasurable fromUnit = unitFactory.getUnit(req.getFromUnit());
        IMeasurable toUnit   = unitFactory.getUnit(req.getToUnit());

        validateSameDomain(fromUnit, toUnit);

        double baseValue      = fromUnit.convertToBaseUnit(req.getValue());
        double convertedValue = toUnit.convertFromBaseUnit(baseValue);

        return new QuantityDTO(round(convertedValue), toUnit.getUnitName());
    }

    // ─── Add ─────────────────────────────────────────────────────────────────

    public QuantityDTO add(ArithmeticRequestDTO req) {
        IMeasurable u1 = unitFactory.getUnit(req.getUnit1());
        IMeasurable u2 = unitFactory.getUnit(req.getUnit2());

        validateSameDomain(u1, u2);
        u1.validateArithmeticSupport("addition");   // blocks Temperature

        double resultBase = u1.convertToBaseUnit(req.getValue1())
                          + u2.convertToBaseUnit(req.getValue2());

        IMeasurable resultUnit = resolveResultUnit(req.getResultUnit(), u1);
        return new QuantityDTO(round(resultUnit.convertFromBaseUnit(resultBase)),
                               resultUnit.getUnitName());
    }

    // ─── Subtract ────────────────────────────────────────────────────────────

    public QuantityDTO subtract(ArithmeticRequestDTO req) {
        IMeasurable u1 = unitFactory.getUnit(req.getUnit1());
        IMeasurable u2 = unitFactory.getUnit(req.getUnit2());

        validateSameDomain(u1, u2);
        u1.validateArithmeticSupport("subtraction");   // blocks Temperature

        double resultBase = u1.convertToBaseUnit(req.getValue1())
                          - u2.convertToBaseUnit(req.getValue2());

        IMeasurable resultUnit = resolveResultUnit(req.getResultUnit(), u1);
        return new QuantityDTO(round(resultUnit.convertFromBaseUnit(resultBase)),
                               resultUnit.getUnitName());
    }

    // ─── Divide ──────────────────────────────────────────────────────────────

    public double divide(ArithmeticRequestDTO req) {
        IMeasurable u1 = unitFactory.getUnit(req.getUnit1());
        IMeasurable u2 = unitFactory.getUnit(req.getUnit2());

        validateSameDomain(u1, u2);

        double base1 = u1.convertToBaseUnit(req.getValue1());
        double base2 = u2.convertToBaseUnit(req.getValue2());

        if (Math.abs(base2) < 1e-9)
            throw new ArithmeticException("Division by zero is not allowed.");

        return round(base1 / base2);
    }

    // ─── Compare ─────────────────────────────────────────────────────────────

    public int compare(CompareRequestDTO req) {
        IMeasurable u1 = unitFactory.getUnit(req.getUnit1());
        IMeasurable u2 = unitFactory.getUnit(req.getUnit2());

        validateSameDomain(u1, u2);

        double base1 = u1.convertToBaseUnit(req.getValue1());
        double base2 = u2.convertToBaseUnit(req.getValue2());

        return Integer.compare(Double.compare(base1, base2), 0);
    }

    // ─── Shared Helpers ───────────────────────────────────────────────────────

    private void validateSameDomain(IMeasurable u1, IMeasurable u2) {
        Class<?> c1 = u1.getClass().isAnonymousClass() ? u1.getClass().getSuperclass() : u1.getClass();
        Class<?> c2 = u2.getClass().isAnonymousClass() ? u2.getClass().getSuperclass() : u2.getClass();

        if (c1 != c2)
            throw new IllegalArgumentException(
                "Units must belong to the same measurement domain: '"
                + u1.getUnitName() + "' and '" + u2.getUnitName() + "'.");
    }

    private IMeasurable resolveResultUnit(String resultUnitName, IMeasurable fallback) {
        if (resultUnitName == null || resultUnitName.isBlank()) return fallback;
        return unitFactory.getUnit(resultUnitName);
    }

    private static double round(double value) {
        return Math.round(value * 1_000_000.0) / 1_000_000.0;
    }
}