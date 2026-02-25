package com.assesment.weather_forecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherStatsDTO {
    private String month;
    private Double maxTemperature;
    private Double medianTemperature;
    private Double minTemperature;
}
