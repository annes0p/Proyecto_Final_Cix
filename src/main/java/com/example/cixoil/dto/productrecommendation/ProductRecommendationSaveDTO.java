package com.example.cixoil.dto.productrecommendation;

import com.example.cixoil.enums.Priority;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRecommendationSaveDTO(
        @NotNull(message = "El modelo de vehículo es obligatorio")
        Long idVehicleModel,

        @NotNull(message = "El tipo de uso de vehículo es obligatorio")
        Long idVehicleUseType,

        @NotNull(message = "El producto es obligatorio")
        Long idProduct,

        @Size(max = 500, message = "La razón no debe superar los 500 caracteres")
        String reason,

        @NotNull(message = "La prioridad es obligatoria")
        Priority priority
) {
}
