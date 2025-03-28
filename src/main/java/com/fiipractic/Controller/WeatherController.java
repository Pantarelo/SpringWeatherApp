package com.fiipractic.Controller;

import com.fiipractic.DTO.WeatherApiResponse;
import com.fiipractic.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/details")
    public WeatherApiResponse getWeatherByLatAndLot(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getWeatherByLatAndLon(lat, lon);
    }


}
