package com.example.cixoil.dto.vehiclemodel;

import com.example.cixoil.enums.FuelType;
import com.example.cixoil.enums.TransmissionType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VehicleModelSaveDTO(
        @NotBlank(message = "El modelo es obligatorio")
        @Size(max = 100, message = "El modelo no debe superar los 100 caracteres")
        String model,

        @NotNull(message = "El año es obligatorio")
        @Min(value = 1900, message = "El año debe ser mayor a 1900")
        @Max(value = 2100, message = "El año debe ser menor a 2100")
        Integer year,

        @NotNull(message = "La marca de vehículo es obligatoria")
        Long idVehicleBrand,

        @NotNull(message = "El tipo de vehículo es obligatorio")
        Long idVehicleType,

        @Positive(message = "La potencia debe ser mayor a 0")
        Integer horsePower,

        @Positive(message = "La cilindrada debe ser mayor a 0")
        Integer motorCC,

        FuelType fuelType,

        TransmissionType transmissionType
) {
}
