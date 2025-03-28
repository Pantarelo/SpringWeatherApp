package com.fiipractic.controllers;

import com.fiipractic.Controller.WeatherController;
import com.fiipractic.DTO.ConditionDTO;
import com.fiipractic.DTO.CurrentDTO;
import com.fiipractic.DTO.LocationDTO;
import com.fiipractic.DTO.WeatherApiResponse;
import com.fiipractic.Service.WeatherService;
import com.fiipractic.configurationTest.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
@Import(TestConfig.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WeatherService weatherService;

    @Test
    void givenValidCoordinates_whenGetWeatherByLatAndLon_thenReturnsWeatherApiResponse() throws Exception {
        double lat = 37.7749;
        double lon = -122.4194;

        LocationDTO mockLocation = new LocationDTO(
                "San Francisco", "California", "USA",
                lat, lon, "America/Los_Angeles",
                1672531199L, "2024-03-27 12:00"
        );

        CurrentDTO mockCurrent = new CurrentDTO(
                1672531199L, "2024-03-27 12:00", 20.0, 68.0,
                true, new ConditionDTO("Sunny", "sunny.png", 1000),
                10.0, 16.0, 180, "S",
                1013.0, 29.91, 0.0, 0.0, 60,
                20, 21.0, 69.8, 21.0, 69.8,
                22.0, 71.6, 10.0, 50.0,
                9.9, 6.2, 5.0, 15.0, 24.0
        );

        WeatherApiResponse mockResponse = new WeatherApiResponse(mockLocation, mockCurrent);

        when(weatherService.getWeatherByLatAndLon(lat, lon))
                .thenReturn(mockResponse);

        mockMvc.perform(get("/weather/details")
                        .param("lat", String.valueOf(lat))
                        .param("lon", String.valueOf(lon)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location.name").value("San Francisco"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.current.temp_c").value(20.0));
    }
}
