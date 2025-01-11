package com.example.java53spring;


import com.example.java53spring.Calculation;
import com.example.java53spring.CalculationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    private final CalculationRepository repository;

    public CalculationService(CalculationRepository repository) {
        this.repository = repository;
    }

    public Calculation saveCalculation(Calculation calculation) {
        return repository.save(calculation);
    }

    public List<Calculation> getAllCalculations() {
        return repository.findAll();
    }
}
