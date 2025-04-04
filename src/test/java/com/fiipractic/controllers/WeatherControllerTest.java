//package com.fiipractic.controllers;
//
//import com.fiipractic.controller.WeatherController;
//import com.fiipractic.DTO.CurrentDTO;
//import com.fiipractic.DTO.LocationDTO;
//import com.fiipractic.DTO.WeatherApiResponse;
//import com.fiipractic.service.WeatherService;
//import com.fiipractic.configurationTest.TestConfig;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(WeatherController.class)
//@Import(TestConfig.class)
//public class WeatherControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private WeatherService weatherService;
//
//    @Test
//    void givenValidCoordinates_whenGetWeatherByLatAndLon_thenReturnsWeatherApiResponse() throws Exception {
//        double lat = 37.7749;
//        double lon = -122.4194;
//
//        LocationDTO mockLocation = new LocationDTO(
//                "San Francisco", "California", "USA",
//                "America/Los_Angeles"
//        );
//
//        CurrentDTO mockCurrent = new CurrentDTO(
//                20.0, 16.0, "S", 0.0
//        );
//
//        WeatherApiResponse mockResponse = new WeatherApiResponse(mockLocation, mockCurrent);
//
//        when(weatherService.getWeatherByLatAndLon(lat, lon))
//                .thenReturn(mockResponse);
//
//        mockMvc.perform(get("/weather/details")
//                        .param("lat", String.valueOf(lat))
//                        .param("lon", String.valueOf(lon)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.location.name").value("San Francisco"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.current.temp_c").value(20.0));
//    }
//}
