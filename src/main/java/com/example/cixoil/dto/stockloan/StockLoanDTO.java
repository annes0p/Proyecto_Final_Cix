package com.example.cixoil.dto.stockloan;

import com.example.cixoil.dto.client.ClientRefDTO;
import com.example.cixoil.dto.product.ProductRefDTO;
import com.example.cixoil.enums.LoanStatus;

public record StockLoanDTO(
        Long id,
        Long quantityLoaned,
        Long quantityRemaining,
        LoanStatus loanStatus,
        ClientRefDTO client,
        ProductRefDTO product
) {
}
