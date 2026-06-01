package com.example.cixoil.dto.saledetail;

import com.example.cixoil.dto.product.ProductRefDTO;

import java.math.BigDecimal;

public record SaleDetailSaveDTO(
        Long idProduct,
        Long quantity
) {
}
