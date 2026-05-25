package com.example.cixoil.dto.product;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        String brand,
        String viscosity,
        String description,
        BigDecimal price,
        Integer status,
        String category
) {
}
