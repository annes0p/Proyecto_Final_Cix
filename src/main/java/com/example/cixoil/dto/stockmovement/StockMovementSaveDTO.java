package com.example.cixoil.dto.stockmovement;

import java.time.LocalDateTime;

import com.example.cixoil.enums.StockMovementType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockMovementSaveDTO(
        @NotNull(message = "El producto es obligatorio")
        Long idProduct,

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a 0")
        Long quantity,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        StockMovementType stockMovementType,

        LocalDateTime movementDate
) {
}
