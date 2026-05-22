package com.example.cixoil.dto.auth;

public record LoginResponseDTO(
        AuthDTO auth,
        AuthUserDTO user
) {
}
