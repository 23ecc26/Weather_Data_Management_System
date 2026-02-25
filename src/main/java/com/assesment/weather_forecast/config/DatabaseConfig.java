package com.assesment.weather_forecast.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.assesment.weather_forecast.repository")
@EnableTransactionManagement
public class DatabaseConfig {
}
