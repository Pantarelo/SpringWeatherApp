package com.fiipractic.configurationTest;

import com.fiipractic.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public WeatherService weatherService() {
        return mock(WeatherService.class);
    }
}
