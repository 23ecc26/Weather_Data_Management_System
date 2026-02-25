package com.assesment.weather_forecast.controller;

import com.assesment.weather_forecast.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private WeatherRepository weatherRepository;
    
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearDatabase() {
        weatherRepository.deleteAll();
        return ResponseEntity.ok("Database cleared successfully");
    }
}
