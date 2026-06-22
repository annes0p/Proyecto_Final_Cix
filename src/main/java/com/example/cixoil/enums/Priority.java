package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority implements SelectableEnum {
    HIGH("Alta"),
    MEDIUM("Media"),
    LOW("Baja");

    private final String value;
}
