package com.example.cixoil.dto.productbrand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductBrandSaveDTO(
        @NotBlank(message = "El nombre de la marca es obligatorio")
        @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
        String name
) {
}
