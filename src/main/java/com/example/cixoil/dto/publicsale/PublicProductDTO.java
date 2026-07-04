package com.example.cixoil.dto.publicsale;

import java.math.BigDecimal;

public record PublicProductDTO(
        Long id,
        String name,
        String brandName,
        String categoryName,
        BigDecimal price,
        String viscosity,
        String description,
        String imageUrl
) {
}
