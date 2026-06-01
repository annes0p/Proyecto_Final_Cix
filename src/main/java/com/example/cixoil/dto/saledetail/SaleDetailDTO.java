package com.example.cixoil.dto.saledetail;

import com.example.cixoil.dto.product.ProductRefDTO;

import java.math.BigDecimal;

public record SaleDetailDTO(
        Long id,
        ProductRefDTO product,
        Long quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal,
        BigDecimal taxAmount,
        BigDecimal total
) {
}
