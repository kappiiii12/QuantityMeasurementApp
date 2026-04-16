package com.quantitymeasurementapp.app.controller;

import com.quantitymeasurementapp.app.dto.*;
import com.quantitymeasurementapp.app.entity.Quantity;
import com.quantitymeasurementapp.app.service.MeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * All endpoints live under /api/measurements.
 *
 * Supported unit strings (case-insensitive):
 *   Length      → INCHES, FEET, YARDS, CENTIMETERS
 *   Weight      → MILLIGRAM, GRAM, KILOGRAM, POUND, TONNE
 *   Volume      → MILLILITRE, LITRE, GALLON
 *   Temperature → CELSIUS, FAHRENHEIT, KELVIN
 */
@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService service;

    public MeasurementController(MeasurementService service) {
        this.service = service;
    }

    // ─── CRUD ────────────────────────────────────────────────────────────────

    /**
     * POST /api/measurements
     * Save a measurement to the database.
     * Body: { "value": 5.0, "unit": "FEET" }
     */
    @PostMapping
    public ResponseEntity<Quantity<?>> save(@RequestBody QuantityDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    /**
     * GET /api/measurements
     * Retrieve all saved measurements.
     */
    @GetMapping
    public ResponseEntity<List<Quantity<?>>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ─── CONVERT ─────────────────────────────────────────────────────────────

    /**
     * POST /api/measurements/convert
     * Convert a value from one unit to another.
     * Body: { "value": 1.0, "fromUnit": "FEET", "toUnit": "INCHES" }
     * Works for all domains: Length, Weight, Volume, Temperature.
     */
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(@RequestBody ConversionRequestDTO req) {
        return ResponseEntity.ok(service.convert(req));
    }

    // ─── ADD ─────────────────────────────────────────────────────────────────

    /**
     * POST /api/measurements/add
     * Add two quantities of the same domain.
     * Body: { "value1": 1.0, "unit1": "FEET", "value2": 6.0, "unit2": "INCHES", "resultUnit": "INCHES" }
     * 'resultUnit' is optional; defaults to unit1.
     * NOT supported for Temperature.
     */
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(@RequestBody ArithmeticRequestDTO req) {
        return ResponseEntity.ok(service.add(req));
    }

    // ─── SUBTRACT ────────────────────────────────────────────────────────────

    /**
     * POST /api/measurements/subtract
     * Subtract the second quantity from the first.
     * Body: { "value1": 2.0, "unit1": "KILOGRAM", "value2": 500.0, "unit2": "GRAM", "resultUnit": "GRAM" }
     * NOT supported for Temperature.
     */
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(@RequestBody ArithmeticRequestDTO req) {
        return ResponseEntity.ok(service.subtract(req));
    }

    // ─── DIVIDE ──────────────────────────────────────────────────────────────

    /**
     * POST /api/measurements/divide
     * Divide quantity1 by quantity2 — returns a dimensionless ratio.
     * Body: { "value1": 1.0, "unit1": "GALLON", "value2": 1000.0, "unit2": "MILLILITRE" }
     */
    @PostMapping("/divide")
    public ResponseEntity<Map<String, Double>> divide(@RequestBody ArithmeticRequestDTO req) {
        double ratio = service.divide(req);
        return ResponseEntity.ok(Map.of("ratio", ratio));
    }

    // ─── COMPARE ─────────────────────────────────────────────────────────────

    /**
     * POST /api/measurements/compare
     * Compare two quantities of the same domain.
     * Body: { "value1": 1.0, "unit1": "FEET", "value2": 12.0, "unit2": "INCHES" }
     * Response: { "result": 0 }  →  -1 (less), 0 (equal), 1 (greater)
     */
    @PostMapping("/compare")
    public ResponseEntity<Map<String, Integer>> compare(@RequestBody CompareRequestDTO req) {
        int result = service.compare(req);
        return ResponseEntity.ok(Map.of("result", result));
    }
}