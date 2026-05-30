package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FuelType implements SelectableEnum {
    GASOLINE("Gasolina"),
    DIESEL("Diésel"),
    HYBRID("Híbrido"),
    ELECTRIC("Eléctrico"),
    LPG("GLP"),
    CNG("GNV"),
    PLUG_IN_HYBRID("Híbrido enchufable"),
    HYDROGEN("Hidrógeno");

    private final String value;
}
