package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProgressStatus {
    PENDING("Pendiente"),
    IN_PROGRESS("En curso"),
    COMPLETED("Completado"),
    CANCELED("Cancelado");

    private final String value;
}
