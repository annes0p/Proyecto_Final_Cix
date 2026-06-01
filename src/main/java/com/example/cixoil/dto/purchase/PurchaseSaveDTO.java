package com.example.cixoil.dto.purchase;

import com.example.cixoil.dto.purchasedetail.PurchaseDetailSaveDTO;

import java.time.LocalDate;
import java.util.List;

public record PurchaseSaveDTO(
        Long idSupplier,
        LocalDate purchasedAt,
        LocalDate estimatedDeliveryAt,
        LocalDate deliveredAt,
        List<PurchaseDetailSaveDTO> details
) {
}
