package com.fiipractic.services;

import com.fiipractic.DTO.CurrentDTO;
import com.fiipractic.DTO.LocationDTO;
import com.fiipractic.DTO.WeatherApiResponse;
import com.fiipractic.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    @InjectMocks
    private WeatherService weatherService;
    @Mock
    private RestTemplate restTemplate;

    @Test
    void givenValidCoordinates_whenGetWeatherByLatAndLon_thenReturnsWeatherApiResponse() {
        double lat = 37.7749;
        double lon = -122.4194;

        LocationDTO mockLocation = new LocationDTO(
                "San Francisco", "California", "USA",
                "America/Los_Angeles"
        );

        CurrentDTO mockCurrent = new CurrentDTO(
                20.0, 16.0, "S", 0.0
        );

        WeatherApiResponse mockResponse = new WeatherApiResponse(mockLocation, mockCurrent);

        when(restTemplate.getForObject(anyString(), eq(WeatherApiResponse.class)))
                .thenReturn(mockResponse);

        WeatherApiResponse result = weatherService.getWeatherByLatAndLon(37.7749, -122.4194);

        assertNotNull(result);
        assertEquals("San Francisco", result.getLocationDTO().name());
    }

    @Test
    void givenInvalidCoordinates_whenGetWeatherByLatAndLon_thenReturnsWeatherApiResponse() {
        double lat = 37.7749;
        double lon = 124352.34;

        when(restTemplate.getForObject(anyString(), eq(WeatherApiResponse.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid request"));

        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class, () -> weatherService.getWeatherByLatAndLon(lat, lon)
        );

        Assertions.assertTrue(thrown.getMessage().contains("Api request fail in getWeatherByLatAndLon"));
    }


}
