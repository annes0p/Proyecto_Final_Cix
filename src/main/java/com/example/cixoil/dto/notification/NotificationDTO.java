package com.example.cixoil.dto.notification;

import com.example.cixoil.enums.NotificationStatus;

import java.time.LocalDateTime;

public record NotificationDTO(
    Long id,
    String title,
    String message,
    NotificationStatus notificationStatus,
    LocalDateTime createdAt
) {
}
