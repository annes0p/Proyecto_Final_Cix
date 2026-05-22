package com.example.cixoil.dto.auth;

public record AuthDTO(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long expiresIn
) {
}
