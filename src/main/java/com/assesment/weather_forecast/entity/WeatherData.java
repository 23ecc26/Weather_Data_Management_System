package com.assesment.weather_forecast.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "weather_data", indexes = {
    @Index(name = "idx_date", columnList = "date"),
    @Index(name = "idx_temperature", columnList = "temperature")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private Double temperature;
    
    @Column(nullable = false)
    private Double humidity;
    
    @Column(nullable = false)
    private Double pressure;
    
    @Column(name = "weather_condition")
    private String weatherCondition;
    
    @Column(name = "heat_index")
    private Double heatIndex;
}
