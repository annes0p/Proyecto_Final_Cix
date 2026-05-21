package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoanStatus {
    PENDING("Pendiente"),
    PARTIALLY_RETURNED("Parcialmente devuelto"),
    RETURNED("Devuelto");

    private final String value;
}
