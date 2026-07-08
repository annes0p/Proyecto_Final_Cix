package com.example.cixoil.dto.trip;

public record TripMessageDTO(
        Long id,
        String sender,
        String senderName,
        String content,
        String createdAt
) {
}
