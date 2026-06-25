package com.example.cixoil.dto.incident;

import com.example.cixoil.dto.incidentcategory.IncidentCategoryRefDTO;
import com.example.cixoil.dto.incidenttype.IncidentTypeRefDTO;
import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.enums.Priority;

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
        String fullTitle
) {
}
