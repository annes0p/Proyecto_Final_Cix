package com.example.cixoil.dto.purchase;

import com.example.cixoil.dto.purchasedetail.PurchaseDetailSaveDTO;
import com.example.cixoil.enums.ReceptionStatus;

import java.time.LocalDate;
import java.util.List;

public record PurchaseSaveDTO(
        Long idSupplier,
        LocalDate purchasedAt,
        LocalDate estimatedDeliveryAt,
        LocalDate deliveredAt,
        ReceptionStatus receptionStatus,
        List<PurchaseDetailSaveDTO> details
) {
}
