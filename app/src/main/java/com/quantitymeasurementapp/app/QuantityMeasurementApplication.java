package com.quantitymeasurementapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.quantitymeasurementapp.app.entity.Quantity;
import com.quantitymeasurementapp.app.repository.MeasurementRepository;
import com.quantitymeasurementapp.app.service.LengthUnit;

@SpringBootApplication
public class QuantityMeasurementApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QuantityMeasurementApplication.class, args);
        
        MeasurementRepository repo = context.getBean(MeasurementRepository.class);

        // Test database persistence
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        repo.save(length);

        System.out.println("Saved to Database! Check H2 Console at /h2-console");
    }
}