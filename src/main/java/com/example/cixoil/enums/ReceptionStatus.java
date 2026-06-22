package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReceptionStatus {
    PENDING("Pendiente"),
    PARTIALLY_RECEIVED("Recibido parcialmente"),
    RECEIVED("Recibido");

    private final String value;
}

