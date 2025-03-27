package com.fiipractic.Service;

import com.fiipractic.DTO.LocationDTO;
import com.fiipractic.DTO.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiWeatherKey;

    public WeatherApiResponse getWeatherByLatAndLon(double lat, double lon) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiWeatherKey + "&q=" + lat + "," + lon + "aqi=no";

        RestTemplate restTemplate = new RestTemplate();
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        if (response != null) {
            return new WeatherApiResponse(response.getLocationDTO(), response.getCurrentDTO());
        }
        else {
            throw new RuntimeException("error to fetch weather details");
        }
    }
}
