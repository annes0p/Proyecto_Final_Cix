package com.example.cixoil.dto.inventory;

public record InventorySaveDTO(
        Long idProduct,
        Long stock,
        Long minStock
) {
}
