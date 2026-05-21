package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
    PENDING("Pendiente"),
    COMPLETED("Pagada"),
    CANCELED("Anulada");

    private final String value;
}
