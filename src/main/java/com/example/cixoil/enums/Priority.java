package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    HIGH("Alta"),
    MEDIUM("Media"),
    LOW("Baja");

    private final String value;
}
