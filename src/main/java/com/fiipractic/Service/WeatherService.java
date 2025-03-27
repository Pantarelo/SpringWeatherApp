package com.fiipractic.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiWeatherKey;

    public String getWeatherByLatAndLot(double lat, double lon) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiWeatherKey + "&q=" + lat + "," + lon + "aqi=no";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        return response;
    }
}
