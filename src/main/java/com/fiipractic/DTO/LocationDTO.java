package com.fiipractic.DTO;

public record LocationDTO(
        String name,
        String region,
        String country,
        double lat,
        double lon,
        String tz_id,
        long localtime_epoch,
        String localtime
){}
