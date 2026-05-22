package com.example.cixoil.dto.auth;

public record RefreshTokenResponseDTO(
        String accessToken,
        String tokenType,
        Long expiresIn
) {
}
