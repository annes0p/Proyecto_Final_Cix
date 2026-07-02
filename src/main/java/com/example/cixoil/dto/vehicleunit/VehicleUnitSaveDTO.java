package com.example.cixoil.dto.vehicleunit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VehicleUnitSaveDTO(
        @NotNull(message = "El modelo de vehículo es obligatorio")
        Long idVehicleModel,

        @NotNull(message = "El cliente es obligatorio")
        Long idClient,

        @NotNull(message = "El tipo de uso de vehículo es obligatorio")
        Long idVehicleUseType,

        @NotBlank(message = "La placa es obligatoria")
        @Size(max = 20, message = "La placa no debe superar los 20 caracteres")
        String plate,

        @Size(max = 30, message = "El color no debe superar los 30 caracteres")
        String color
) {
}
