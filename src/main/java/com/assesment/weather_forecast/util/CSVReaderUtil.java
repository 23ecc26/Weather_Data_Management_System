package com.assesment.weather_forecast.util;

import com.assesment.weather_forecast.entity.WeatherData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CSVReaderUtil {
    
    public List<WeatherData> readWeatherDataFromCSV(String filePath) throws IOException, CsvException {
        List<WeatherData> weatherDataList = new ArrayList<>();
        Set<LocalDate> seenDates = new HashSet<>();
        
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            
            if (rows.isEmpty()) return weatherDataList;
            
            // Detect format from header
            String[] header = rows.get(0);
            boolean isSimpleFormat = header[0].trim().equalsIgnoreCase("date");
            
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                
                try {
                    WeatherData data = isSimpleFormat ? parseSimpleRow(row) : parseComplexRow(row);
                    
                    if (data != null && data.getDate() != null && !seenDates.contains(data.getDate())) {
                        weatherDataList.add(data);
                        seenDates.add(data.getDate());
                    }
                } catch (Exception e) {
                    System.err.println("Skipping invalid row: " + String.join(",", row));
                }
            }
        }
        
        return weatherDataList;
    }
    
    private WeatherData parseSimpleRow(String[] row) {
        if (row.length < 5) return null;
        
        WeatherData data = new WeatherData();
        
        LocalDate date = parseSimpleDate(row[0]);
        if (date == null) return null;
        data.setDate(date);
        
        Double temperature = parseDouble(row[1]);
        if (temperature == null) return null;
        data.setTemperature(temperature);
        
        Double humidity = parseDouble(row[2]);
        if (humidity == null) return null;
        data.setHumidity(humidity);
        
        Double pressure = parseDouble(row[3]);
        if (pressure == null) return null;
        data.setPressure(pressure);
        
        data.setWeatherCondition(row[4].trim());
        
        if (row.length > 5) {
            data.setHeatIndex(parseDouble(row[5]));
        }
        
        return data;
    }
    
    private WeatherData parseComplexRow(String[] row) {
        if (row.length < 12) return null;
        
        WeatherData data = new WeatherData();
        
        LocalDate date = parseDateTime(row[0]);
        if (date == null) return null;
        data.setDate(date);
        
        Double temperature = parseDouble(row[11]);
        if (temperature == null) return null;
        data.setTemperature(temperature);
        
        Double humidity = parseDouble(row[6]);
        if (humidity == null) return null;
        data.setHumidity(humidity);
        
        Double pressure = parseDouble(row[8]);
        if (pressure == null || pressure == -9999) return null;
        data.setPressure(pressure);
        
        data.setWeatherCondition(row[1].trim());
        data.setHeatIndex(parseDouble(row[5]));
        
        return data;
    }
    
    private LocalDate parseSimpleDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        
        try {
            return LocalDate.parse(dateStr.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return null;
        }
    }
    
    private LocalDate parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) return null;
        
        try {
            // Format: yyyyMMdd-HH:mm -> extract date part
            String datePart = dateTimeStr.trim().split("-")[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return LocalDate.parse(datePart, formatter);
        } catch (Exception e) {
            return null;
        }
    }
    
    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        
        try {
            double val = Double.parseDouble(value.trim());
            return (val == -9999) ? null : val;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
