package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StockMovementType implements SelectableEnum {
    IN("Entrada"),
    OUT("Salida"),
    ADJUSTMENT_IN("Ajuste (entrada)"),
    ADJUSTMENT_OUT("Ajuste (salida)"),
    SALE_CANCELLATION("Anulación de venta"),
    SALE_RETURN("Devolución de venta"),
    PURCHASE_RETURN("Devolución de compra");

    private final String value;

    public boolean isAddition() {
        return switch (this) {
            case IN, ADJUSTMENT_IN, SALE_RETURN, SALE_CANCELLATION -> true;
            case OUT, ADJUSTMENT_OUT, PURCHASE_RETURN -> false;
        };
    }
}
