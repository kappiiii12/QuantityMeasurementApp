package com.quantitymeasurementapp.app.repository;

import com.quantitymeasurementapp.app.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Quantity<?>, Long> {
    // Basic CRUD operations are inherited
}