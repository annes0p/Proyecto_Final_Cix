package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VoucherType {
    SALE_NOTE("Nota de venta"),
    INVOICE("Factura"),
    RECEIPT("Boleta");

    private final String value;
}
