package com.example.cixoil.dto.inventory;

import com.example.cixoil.dto.product.ProductRefDTO;

public record InventoryDTO(
        Long id,
        ProductRefDTO product,
        Long stock,
        Long minStock
) {
}
