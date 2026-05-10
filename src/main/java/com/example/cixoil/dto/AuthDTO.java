package com.example.cixoil.dto;

public record AuthDTO(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long expiresIn
) {
}
