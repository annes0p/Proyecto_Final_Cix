package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod implements SelectableEnum {
    YAPE("Yape"),
    CASH("Efectivo"),
    CARD("Tarjeta"),
    TRANSFER("Transferencia");

    private final String value;
}
