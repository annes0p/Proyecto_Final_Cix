package com.example.cixoil.dto.purchase;

import java.time.LocalDate;
import java.util.List;

import com.example.cixoil.dto.purchasedetail.PurchaseDetailSaveDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PurchaseSaveDTO(
        @NotNull(message = "El proveedor es obligatorio")
        Long idSupplier,

        @NotNull(message = "La fecha de compra es obligatoria")
        LocalDate purchasedAt,

        LocalDate estimatedDeliveryAt,

        LocalDate deliveredAt,

        @NotEmpty(message = "La compra debe tener al menos un detalle")
        @Valid
        List<PurchaseDetailSaveDTO> details
) {
}
