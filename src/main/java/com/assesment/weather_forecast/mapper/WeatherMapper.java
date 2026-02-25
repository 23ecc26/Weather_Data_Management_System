package com.assesment.weather_forecast.mapper;

import com.assesment.weather_forecast.dto.WeatherDTO;
import com.assesment.weather_forecast.entity.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {
    
    public WeatherDTO toDTO(WeatherData entity) {
        if (entity == null) return null;
        return new WeatherDTO(
            entity.getDate(),
            entity.getWeatherCondition(),
            entity.getTemperature(),
            entity.getHumidity(),
            entity.getPressure(),
            entity.getHeatIndex()
        );
    }
    
    public WeatherData toEntity(WeatherDTO dto) {
        if (dto == null) return null;
        WeatherData entity = new WeatherData();
        entity.setDate(dto.getDate());
        entity.setWeatherCondition(dto.getWeatherCondition());
        entity.setTemperature(dto.getTemperature());
        entity.setHumidity(dto.getHumidity());
        entity.setPressure(dto.getPressure());
        entity.setHeatIndex(dto.getHeatIndex());
        return entity;
    }
}
