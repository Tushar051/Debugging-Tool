package com.dubbgingSystem.debugger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/health-check")
public class HealthCheck {

    @GetMapping
    public Map<String, String> health(){
        return Map.of("status", "UP", "timestamp", LocalDateTime.now().toString());
    }
}
