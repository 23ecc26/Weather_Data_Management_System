package com.assesment.weather_forecast.controller;

import com.assesment.weather_forecast.dto.WeatherDTO;
import com.assesment.weather_forecast.dto.WeatherStatsDTO;
import com.assesment.weather_forecast.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @PostMapping("/import")
    public ResponseEntity<String> importWeatherData(@RequestParam String filePath) {
        try {
            weatherService.importWeatherData(filePath);
            return ResponseEntity.ok("Weather data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to import data: " + e.getMessage());
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadWeatherData(@RequestParam("file") MultipartFile file) {
        try {
            String tempPath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
            file.transferTo(new java.io.File(tempPath));
            weatherService.importWeatherData(tempPath);
            return ResponseEntity.ok("Weather data uploaded and imported successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload data: " + e.getMessage());
        }
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<WeatherDTO> getWeatherByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        WeatherDTO weather = weatherService.getWeatherByDate(localDate);
        
        if (weather == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(weather);
    }
    
    @GetMapping("/month/{month}")
    public ResponseEntity<List<WeatherDTO>> getWeatherByMonth(@PathVariable int month) {
        if (month < 1 || month > 12) {
            return ResponseEntity.badRequest().build();
        }
        
        List<WeatherDTO> weatherList = weatherService.getWeatherByMonth(month);
        return ResponseEntity.ok(weatherList);
    }
    
    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<List<WeatherDTO>> getWeatherByYearAndMonth(
            @PathVariable int year, 
            @PathVariable int month) {
        
        if (month < 1 || month > 12) {
            return ResponseEntity.badRequest().build();
        }
        
        List<WeatherDTO> weatherList = weatherService.getWeatherByYearAndMonth(year, month);
        return ResponseEntity.ok(weatherList);
    }
    
    @GetMapping("/stats/{year}")
    public ResponseEntity<List<WeatherStatsDTO>> getTemperatureStatsByYear(@PathVariable int year) {
        List<WeatherStatsDTO> stats = weatherService.getTemperatureStatsByYear(year);
        return ResponseEntity.ok(stats);
    }
}
