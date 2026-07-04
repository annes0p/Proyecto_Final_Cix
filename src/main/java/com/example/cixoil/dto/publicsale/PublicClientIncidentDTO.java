package com.example.cixoil.dto.publicsale;

public record PublicClientIncidentDTO(
        Long id,
        String title,
        String incidentType,
        String incidentStatus,
        String priority,
        String description,
        String resolutionNote,
        Integer rating,
        String createdAt,
        String resolvedAt,
        String ratingToken
) {
}
