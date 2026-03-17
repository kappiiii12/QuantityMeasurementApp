package com.quantitymeasurementapp.app.controller;

import com.quantitymeasurementapp.app.dto.ConversionRequestDTO;
import com.quantitymeasurementapp.app.dto.QuantityDTO;
import com.quantitymeasurementapp.app.entity.IMeasurable;
import com.quantitymeasurementapp.app.entity.Quantity;
import com.quantitymeasurementapp.app.repository.MeasurementRepository;
import com.quantitymeasurementapp.app.service.UnitFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementRepository repository;

    @Autowired
    private UnitFactory unitFactory;

    @PostMapping
    public Quantity<?> addMeasurement(@RequestBody QuantityDTO dto) {
        IMeasurable unit = unitFactory.getUnit(dto.getUnit());
        // We cast to raw Quantity because the specific generic type is determined at runtime
        Quantity entity = new Quantity(dto.getValue(), unit);
        return repository.save(entity);
    }
    
    @PostMapping("/convert")
    public QuantityDTO convertMeasurement(@RequestBody ConversionRequestDTO request) {
        // 1. Resolve units using the Factory
        IMeasurable fromUnit = unitFactory.getUnit(request.getFromUnit());
        IMeasurable toUnit = unitFactory.getUnit(request.getToUnit());

        // 2. Create a temporary Quantity and perform conversion
        // We use raw types here because the generic is dynamic at runtime
        Quantity sourceQuantity = new Quantity(request.getValue(), fromUnit);
        Quantity resultQuantity = sourceQuantity.convertTo(toUnit);

        // 3. Return the result as a DTO
        return new QuantityDTO(resultQuantity.getValue(), toUnit.getUnitName());
    }

    @GetMapping
    public List<Quantity<?>> getAll() {
        return repository.findAll();
    }
}
