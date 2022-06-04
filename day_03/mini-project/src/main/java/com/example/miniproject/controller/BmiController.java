package com.example.miniproject.controller;

import com.example.miniproject.request.BmiRequest;
import com.example.miniproject.service.BmiService;
import com.example.miniproject.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BmiController {
    private final BmiService bmiService;

    @GetMapping("/bmi-get")
    public double calculateBmiGet(@RequestParam double height, @RequestParam double weight) {
        return bmiService.calculateBmi(height, weight);
    }

    @PostMapping("/bmi-post")
    public double calculateBmiPost(@RequestBody BmiRequest request) {
        return bmiService.calculateBmi(request.getHeight(), request.getWeight());
    }
}
