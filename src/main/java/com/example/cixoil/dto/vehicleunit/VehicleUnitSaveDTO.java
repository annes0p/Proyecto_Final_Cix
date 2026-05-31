package com.example.cixoil.dto.vehicleunit;

public record VehicleUnitSaveDTO(
        Long idVehicleModel,
        Long idClient,
        Long idVehicleUseType,
        String plate,
        String color
) {
}
