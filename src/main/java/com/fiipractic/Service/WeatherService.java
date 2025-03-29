package com.fiipractic.Service;

import com.fiipractic.DTO.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiWeatherKey;
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherApiResponse getWeatherByLatAndLon(double lat, double lon) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiWeatherKey + "&q=" + lat + "," + lon + "&aqi=no";

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            return new WeatherApiResponse(response.getLocationDTO(), response.getCurrentDTO());
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Api request fail in getWeatherByLatAndLon: " + e.getMessage() + " " + e);
        }
    }
}
