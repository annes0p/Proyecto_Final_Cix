package com.example.cixoil.dto.purchase;

import com.example.cixoil.dto.purchasedetail.PurchaseDetailDTO;
import com.example.cixoil.dto.supplier.SupplierDTO;
import com.example.cixoil.enums.ReceptionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PurchaseDTO(
        Long id,
        SupplierDTO supplier,
        LocalDate purchasedAt,
        LocalDate estimatedDeliveryAt,
        LocalDate deliveredAt,
        BigDecimal total,
        ReceptionStatus receptionStatus,
        List<PurchaseDetailDTO> details
) {
}
