package com.example.cixoil.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "El usuario o correo es obligatorio")
        String identifier,

        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}
