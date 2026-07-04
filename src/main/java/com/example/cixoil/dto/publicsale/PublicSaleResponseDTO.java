package com.example.cixoil.dto.publicsale;

import java.math.BigDecimal;

public record PublicSaleResponseDTO(
        Long saleId,
        BigDecimal total,
        String message
) {
}
