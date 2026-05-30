package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DocumentType implements SelectableEnum {
    DNI("DNI"),
    RUC("RUC");

    private final String value;
}
