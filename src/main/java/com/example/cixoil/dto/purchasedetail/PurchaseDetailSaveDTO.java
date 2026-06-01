package com.example.cixoil.dto.purchasedetail;

import java.math.BigDecimal;

public record PurchaseDetailSaveDTO(
        Long idProduct,
        Long quantity
) {
}
