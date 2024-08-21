package com.ranjit.assignment.Problem1;

import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculationService {
    private ScriptEngine engine;

    public CalculationService() {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            engine = manager.getEngineByName("JavaScript");
            if (engine == null) {
                throw new RuntimeException("JavaScript engine not found. Make sure you are using JDK with Nashorn engine.");
            }
        } catch (Exception e) {
            // Log the exception and handle the error gracefully
            System.err.println("Error initializing ScriptEngine: " + e.getMessage());
            throw new RuntimeException("Failed to initialize ScriptEngine", e);
        }
    }

    public Map<String, Double> calculate(List<Calculation> calculations) throws ScriptException {
        Map<String, Double> results = new HashMap<>();

        for (Calculation calculation : calculations) {
            String fieldName = calculation.getCalculateField();
            String formula = calculation.getFormula();

            // Replace the fields in the formula with their calculated values
            for (Map.Entry<String, Double> entry : results.entrySet()) {
                formula = formula.replace(entry.getKey(), entry.getValue().toString());
            }

            // Evaluate the formula using JavaScript engine
            Double value = (Double) engine.eval(formula);

            // Store the result
            results.put(fieldName, value);
        }

        return results;
    }
}
