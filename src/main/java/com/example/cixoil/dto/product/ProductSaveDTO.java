package com.example.cixoil.dto.product;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductSaveDTO(
        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 150, message = "El nombre no debe superar los 150 caracteres")
        String name,

        @NotNull(message = "La marca es obligatoria")
        Long idBrand,

        @Size(max = 50, message = "La viscosidad no debe superar los 50 caracteres")
        String viscosity,

        @Size(max = 1000, message = "La descripción no debe superar los 1000 caracteres")
        String description,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a 0")
        BigDecimal price,

        @NotNull(message = "La categoría es obligatoria")
        Long idCategory,

        MultipartFile image
) {
}
