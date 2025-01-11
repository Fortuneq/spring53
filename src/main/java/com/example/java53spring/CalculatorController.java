package com.example.java53spring;

import com.example.java53spring.Calculation;
import com.example.java53spring.CalculationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    private final CalculationService calculationService;

    public CalculatorController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/")
    public String showCalculator() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam double operand1,
            @RequestParam double operand2,
            @RequestParam String operation,
            Model model) {

        double result = switch (operation) {
            case "add" -> operand1 + operand2;
            case "subtract" -> operand1 - operand2;
            case "multiply" -> operand1 * operand2;
            case "divide" -> operand2 != 0 ? operand1 / operand2 : Double.NaN;
            default -> throw new IllegalArgumentException("Invalid operation");
        };

        Calculation calculation = new Calculation();
        calculation.setOperand1(operand1);
        calculation.setOperand2(operand2);
        calculation.setOperation(operation);
        calculation.setResult(result);

        calculationService.saveCalculation(calculation);

        model.addAttribute("result", result);
        return "index";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {
        model.addAttribute("calculations", calculationService.getAllCalculations());
        return "history";
    }
}
