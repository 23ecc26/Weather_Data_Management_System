package com.assesment.weather_forecast.service.impl;

import com.assesment.weather_forecast.dto.WeatherDTO;
import com.assesment.weather_forecast.dto.WeatherStatsDTO;
import com.assesment.weather_forecast.entity.WeatherData;
import com.assesment.weather_forecast.mapper.WeatherMapper;
import com.assesment.weather_forecast.repository.WeatherRepository;
import com.assesment.weather_forecast.service.WeatherService;
import com.assesment.weather_forecast.util.CSVReaderUtil;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
    
    @Autowired
    private WeatherRepository weatherRepository;
    
    @Autowired
    private WeatherMapper weatherMapper;
    
    @Autowired
    private CSVReaderUtil csvReaderUtil;
    
    @Override
    @Transactional
    public void importWeatherData(String filePath) {
        try {
            List<WeatherData> weatherDataList = csvReaderUtil.readWeatherDataFromCSV(filePath);
            weatherRepository.saveAll(weatherDataList);
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Failed to import weather data: " + e.getMessage(), e);
        }
    }
    
    @Override
    public WeatherDTO getWeatherByDate(LocalDate date) {
        return weatherRepository.findByDate(date)
            .map(weatherMapper::toDTO)
            .orElse(null);
    }
    
    @Override
    public List<WeatherDTO> getWeatherByMonth(int month) {
        return weatherRepository.findByMonth(month).stream()
            .map(weatherMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<WeatherDTO> getWeatherByYearAndMonth(int year, int month) {
        return weatherRepository.findByYearAndMonth(year, month).stream()
            .map(weatherMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<WeatherStatsDTO> getTemperatureStatsByYear(int year) {
        List<WeatherData> yearData = weatherRepository.findByYear(year);
        
        Map<Integer, List<Double>> temperaturesByMonth = yearData.stream()
            .collect(Collectors.groupingBy(
                data -> data.getDate().getMonthValue(),
                Collectors.mapping(WeatherData::getTemperature, Collectors.toList())
            ));
        
        List<WeatherStatsDTO> stats = new ArrayList<>();
        
        for (int month = 1; month <= 12; month++) {
            List<Double> temperatures = temperaturesByMonth.get(month);
            
            if (temperatures != null && !temperatures.isEmpty()) {
                Collections.sort(temperatures);
                
                double max = temperatures.get(temperatures.size() - 1);
                double min = temperatures.get(0);
                double median = calculateMedian(temperatures);
                
                String monthName = Month.of(month).name();
                monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
                
                stats.add(new WeatherStatsDTO(monthName, max, median, min));
            }
        }
        
        return stats;
    }
    
    private double calculateMedian(List<Double> sortedList) {
        int size = sortedList.size();
        if (size % 2 == 0) {
            return (sortedList.get(size / 2 - 1) + sortedList.get(size / 2)) / 2.0;
        } else {
            return sortedList.get(size / 2);
        }
    }
}
