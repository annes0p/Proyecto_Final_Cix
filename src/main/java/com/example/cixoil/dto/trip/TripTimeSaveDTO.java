package com.example.cixoil.dto.trip;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record TripTimeSaveDTO(
        @NotNull(message = "La hora de inicio es obligatoria")
        LocalTime startTime,

        LocalTime endTime
) {
}
