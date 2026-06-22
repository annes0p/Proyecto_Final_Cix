package com.example.cixoil.dto.product;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProductSaveDTO(
        String name,
        Long idBrand,
        String viscosity,
        String description,
        BigDecimal price,
        Long idCategory,
        MultipartFile image
) {
}
