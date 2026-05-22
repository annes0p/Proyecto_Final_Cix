package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransmissionType {
    MANUAL("Manual"),
    AUTOMATIC("Automático"),
    CVT("CVT (Transmisión variable continua)"),
    SEMI_AUTOMATIC("Semiautomático"),
    DUAL_CLUTCH("Doble embrague");

    private final String value;
}
