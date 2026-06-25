package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IncidentStatus {
    OPEN("Abierto"),
    RESOLVED("Resuelto"),
    CLOSED("Cerrado"),
    CANCELED("Cancelado");

    private final String value;
}
