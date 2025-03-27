package com.fiipractic.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherApiResponse {
    @JsonProperty("location")
    private LocationDTO locationDTO;
    @JsonProperty("current")
    private CurrentDTO currentDTO;

    public WeatherApiResponse(LocationDTO locationDTO, CurrentDTO currentDTO) {
        this.locationDTO = locationDTO;
        this.currentDTO = currentDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public CurrentDTO getCurrentDTO() {
        return currentDTO;
    }
}
