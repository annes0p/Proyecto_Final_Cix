package com.example.cixoil.dto.stockloan;

import com.example.cixoil.enums.LoanStatus;

public record StockLoanSaveDTO(
        Long quantityLoaned,
        Long quantityRemaining,
        LoanStatus loanStatus,
        Long idClient,
        Long idProduct
) {
}
