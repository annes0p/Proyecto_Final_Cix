package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StockMovementType {
    IN("Entrada"),
    OUT("Salida"),
    ADJUSTMENT("Ajuste"),
    RETURN("Devolución");

    private final String value;
}
