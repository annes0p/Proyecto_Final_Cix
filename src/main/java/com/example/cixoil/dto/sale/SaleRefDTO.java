package com.example.cixoil.dto.sale;

import com.example.cixoil.dto.client.ClientRefDTO;

import java.math.BigDecimal;

public record SaleRefDTO(
        Long id,
        ClientRefDTO client,
        BigDecimal total
) {
}
