package com.assesment.weather_forecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {
    private LocalDate date;
    private String weatherCondition;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double heatIndex;
}
