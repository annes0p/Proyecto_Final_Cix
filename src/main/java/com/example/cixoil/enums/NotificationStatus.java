package com.example.cixoil.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationStatus {
    UNREAD("No leído"),
    READ("Leído");

    private final String value;
}
