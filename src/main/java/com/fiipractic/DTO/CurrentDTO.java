package com.fiipractic.DTO;

public record CurrentDTO (
        double temp_c,
        double wind_kph,
        String wind_dir,
        double precip_mm
){}
