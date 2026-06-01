package com.example.cixoil.dto.stockmovement;

import com.example.cixoil.enums.StockMovementType;

import java.time.LocalDateTime;

public record StockMovementSaveDTO(
        Long idProduct,
        Long quantity,
        StockMovementType stockMovementType,
        LocalDateTime movementDate
) {
}
