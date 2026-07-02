package com.example.cixoil.dto.productrecommendation;

import jakarta.validation.constraints.NotNull;

public record RecommendationRequestDTO(
        @NotNull(message = "El modelo de vehículo es obligatorio")
        Long idVehicleModel,

        @NotNull(message = "El tipo de uso de vehículo es obligatorio")
        Long idVehicleUseType
) {
}
