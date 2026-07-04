package com.example.cixoil.dto.incident;

import com.example.cixoil.dto.incidentcategory.IncidentCategoryRefDTO;
import com.example.cixoil.dto.incidenttype.IncidentTypeRefDTO;
import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.enums.Priority;

import java.time.LocalDateTime;

public record IncidentDTO(
        Long id,
        String title,
        IncidentTypeRefDTO incidentType,
        Priority priority,
        String description,
        String reportedBy,
        IncidentCategoryRefDTO incidentCategory,
        String reference,
        IncidentStatus incidentStatus,
        String fullTitle,
        String resolutionNote,
        LocalDateTime createdAt,
        LocalDateTime resolvedAt,
        Integer rating
) {
}
