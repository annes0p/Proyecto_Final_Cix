package com.example.cixoil.dto.trip;

public record TripSaveDTO(
        Long idRoute,
        Long idOriginLocation,
        Long idDestinationLocation
) {
}
