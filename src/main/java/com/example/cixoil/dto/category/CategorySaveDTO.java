package com.example.cixoil.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategorySaveDTO(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
        String name,

        @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
        String description
) {
}
