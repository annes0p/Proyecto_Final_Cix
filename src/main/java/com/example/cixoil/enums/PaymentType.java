package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType {
    CASH("Contado"),
    CREDIT("Crédito");

    private final String value;
}
