package com.assesment.weather_forecast.repository;

import com.assesment.weather_forecast.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    
    Optional<WeatherData> findByDate(LocalDate date);
    
    @Query("SELECT w FROM WeatherData w WHERE MONTH(w.date) = :month ORDER BY w.date")
    List<WeatherData> findByMonth(@Param("month") int month);
    
    @Query("SELECT w FROM WeatherData w WHERE YEAR(w.date) = :year AND MONTH(w.date) = :month ORDER BY w.date")
    List<WeatherData> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT w FROM WeatherData w WHERE YEAR(w.date) = :year ORDER BY w.date")
    List<WeatherData> findByYear(@Param("year") int year);
}
