package com.example.cixoil.dto;

public record LoginResponseDTO(
        AuthDTO auth,
        AuthUserDTO user
) {
}
