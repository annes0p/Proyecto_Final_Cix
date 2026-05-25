package com.example.cixoil.dto.product;

import java.math.BigDecimal;

public record ProductSaveDTO(
        String name,
        Long brandId,
        String viscosity,
        String description,
        BigDecimal price,
        Long categoryId
) {
}
