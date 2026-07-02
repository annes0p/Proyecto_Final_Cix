package com.example.cixoil.dto.saledetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleDetailSaveDTO(
        @NotNull(message = "El producto es obligatorio")
        Long idProduct,

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a 0")
        Long quantity
) {
}
