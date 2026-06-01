package com.example.cixoil.dto.stockmovement;

import com.example.cixoil.dto.product.ProductRefDTO;
import com.example.cixoil.enums.StockMovementType;

import java.time.LocalDateTime;

public record StockMovementDTO(
        Long id,
        ProductRefDTO product,
        Long initialStock,
        Long quantity,
        Long finalStock,
        StockMovementType stockMovementType,
        LocalDateTime movementDate
) {
}
