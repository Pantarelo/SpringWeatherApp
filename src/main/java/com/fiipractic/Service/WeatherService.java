package com.fiipractic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiipractic.DTO.WeatherApiResponse;
import com.fiipractic.entity.RequestHistory;
import com.fiipractic.entity.User;
import com.fiipractic.entity.UserProfile;
import com.fiipractic.repository.RequestHistoryRepository;
import com.fiipractic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
//    @Value("${api.weather.key}")
    private String apiWeatherKey;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final RequestHistoryRepository requestHistoryRepository;
    private ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate, UserRepository userRepository, RequestHistoryRepository requestHistoryRepository, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.objectMapper = objectMapper;
    }

    public WeatherApiResponse getWeatherByLatAndLon(Long userId, double lat, double lon) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile profile = user.getUserProfile();
        if (profile == null || profile.getWeatherApiKey() == null) {
            throw new RuntimeException("User does not have a valid weather API key.");
        }

        String apiKey = profile.getWeatherApiKey();

        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey +
                "&q=" + lat + "," + lon + "&aqi=no";

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
            String jsonResponse = objectMapper.writeValueAsString(response);
            System.out.println(jsonResponse);

            saveRequestHistory(user, lat, lon, jsonResponse);

            return response;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Weather API request failed: " + e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveRequestHistory(User user, double lat, double lon, String jsonResponse) {
        RequestHistory history = new RequestHistory();
        history.setUser(user);
        history.setLat(String.valueOf(lat));
        history.setLon(String.valueOf(lon));
        history.setResponse(jsonResponse);
        history.setQ(true);
        history.setApi(true);
        history.setDays(0);
        history.setAlerts(false);

        requestHistoryRepository.save(history);
    }
}
