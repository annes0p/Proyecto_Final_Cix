package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VoucherType implements SelectableEnum {
    SALE_NOTE("Nota de venta"),
    INVOICE("Factura"),
    RECEIPT("Boleta");

    private final String value;
}
