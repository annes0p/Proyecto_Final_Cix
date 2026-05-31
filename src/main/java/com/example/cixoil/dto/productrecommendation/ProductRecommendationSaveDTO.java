package com.example.cixoil.dto.productrecommendation;

import com.example.cixoil.enums.Priority;

public record ProductRecommendationSaveDTO(
        Long idVehicleModel,
        Long idVehicleUseType,
        Long idProduct,
        String reason,
        Priority priority
) {
}
