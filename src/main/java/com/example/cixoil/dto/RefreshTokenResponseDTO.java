package com.example.cixoil.dto;

public record RefreshTokenResponseDTO(
        String accessToken,
        String tokenType,
        Long expiresIn
) {
}
