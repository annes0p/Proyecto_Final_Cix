package com.example.cixoil.dto.stockloan;

import com.example.cixoil.enums.LoanStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record StockLoanSaveDTO(
        @NotNull(message = "La cantidad prestada es obligatoria")
        @Positive(message = "La cantidad prestada debe ser mayor a 0")
        Long quantityLoaned,

        @NotNull(message = "La cantidad restante es obligatoria")
        @PositiveOrZero(message = "La cantidad restante no puede ser negativa")
        Long quantityRemaining,

        @NotNull(message = "El estado del préstamo es obligatorio")
        LoanStatus loanStatus,

        @NotNull(message = "El cliente es obligatorio")
        Long idClient,

        @NotNull(message = "El producto es obligatorio")
        Long idProduct
) {
}
