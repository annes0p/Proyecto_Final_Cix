package com.example.cixoil.dto.purchasedetail;

import com.example.cixoil.dto.product.ProductDTO;

import java.math.BigDecimal;

public record PurchaseDetailDTO(
        Long id,
        ProductDTO product,
        Long quantity,
        Long receivedQuantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) {
}
