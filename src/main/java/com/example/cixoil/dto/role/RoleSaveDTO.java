package com.example.cixoil.dto.role;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RoleSaveDTO(
        @NotBlank(message = "El nombre del rol es obligatorio")
        @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
        String name,

        @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
        String description,

        @NotEmpty(message = "El rol debe tener al menos un módulo asignado")
        List<Long> idModules
) {
}