package com.example.cixoil.dto.trip;

import jakarta.validation.constraints.NotNull;

public record TripSaveDTO(
        @NotNull(message = "La ruta es obligatoria")
        Long idRoute,

        @NotNull(message = "El punto de origen es obligatorio")
        Long idOriginLocation,

        @NotNull(message = "El punto de destino es obligatorio")
        Long idDestinationLocation,

        Long idSale
) {
}
