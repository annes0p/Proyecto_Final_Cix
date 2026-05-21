package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReceptionStatus {
    PENDING("Pendiente"),
    PARTIALLY_RECIEVED("Recibido parcialmente"),
    RECIEVED("Recibido");

    private final String value;
}
