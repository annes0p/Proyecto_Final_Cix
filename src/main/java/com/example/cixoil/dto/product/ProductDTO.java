package com.example.cixoil.dto.product;

import com.example.cixoil.dto.category.CategoryDTO;
import com.example.cixoil.dto.productbrand.ProductBrandDTO;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        ProductBrandDTO brand,
        String viscosity,
        String description,
        BigDecimal price,
        Integer status,
        CategoryDTO category
) {
}
