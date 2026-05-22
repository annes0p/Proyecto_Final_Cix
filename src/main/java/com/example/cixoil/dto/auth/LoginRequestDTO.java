package com.example.cixoil.dto.auth;

public record LoginRequestDTO(
        String identifier,
        String password
) {
}
