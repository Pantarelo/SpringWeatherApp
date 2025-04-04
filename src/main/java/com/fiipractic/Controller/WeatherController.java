package com.fiipractic.controller;

import com.fiipractic.DTO.WeatherApiResponse;
import com.fiipractic.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{userId}")
    public WeatherApiResponse getWeatherByLatAndLot(@PathVariable Long userId, @RequestParam double lat, @RequestParam double lon) {
        return weatherService.getWeatherByLatAndLon(userId, lat, lon);
    }
}
