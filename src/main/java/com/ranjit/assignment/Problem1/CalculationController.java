package com.ranjit.assignment.Problem1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calculations")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @PostMapping("/calculate")
    public Map<String, Double> calculate(@RequestBody List<Calculation> calculations) throws ScriptException {
        return calculationService.calculate(calculations);
    }
}
