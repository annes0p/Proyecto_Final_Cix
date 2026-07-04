package com.example.cixoil.dto.trip;

import jakarta.validation.constraints.NotNull;

public record TripLocationSaveDTO(
        @NotNull(message = "La latitud es obligatoria")
        Double latitude,

        @NotNull(message = "La longitud es obligatoria")
        Double longitude
) {
}
