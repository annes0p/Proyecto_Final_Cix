package com.example.cixoil.dto.productrecommendation;

public record AiRecommendationResponseDTO(
        Long idProduct,
        String reason,
        String priority
) {
}
