package com.quantitymeasurementapp.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantitymeasurementapp.app.entity.Quantity;
import com.quantitymeasurementapp.app.repository.MeasurementRepository;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository repository;

    public Quantity<?> saveMeasurement(Quantity<?> quantity) {
        return repository.save(quantity);
    }

    public List<Quantity<?>> getAllMeasurements() {
        return repository.findAll();
    }
}