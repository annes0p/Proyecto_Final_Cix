package com.example.cixoil.dto.trip;

import java.time.LocalTime;

public record TripTimeSaveDTO(
        LocalTime startTime,
        LocalTime endTime
) {
}
