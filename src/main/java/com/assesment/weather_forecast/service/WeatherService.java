package com.assesment.weather_forecast.service;

import com.assesment.weather_forecast.dto.WeatherDTO;
import com.assesment.weather_forecast.dto.WeatherStatsDTO;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {
    
    void importWeatherData(String filePath);
    
    WeatherDTO getWeatherByDate(LocalDate date);
    
    List<WeatherDTO> getWeatherByMonth(int month);
    
    List<WeatherDTO> getWeatherByYearAndMonth(int year, int month);
    
    List<WeatherStatsDTO> getTemperatureStatsByYear(int year);
}
