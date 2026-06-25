package com.example.cixoil.dto.incident;

import com.example.cixoil.enums.Priority;

public record IncidentSaveDTO(
        String title,
        Long idIncidentType,
        Priority priority,
        String description,
        String reportedBy,
        Long idIncidentCategory,
        String reference
) {
}
