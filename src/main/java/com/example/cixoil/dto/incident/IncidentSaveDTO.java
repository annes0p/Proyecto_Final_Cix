package com.example.cixoil.dto.incident;

import com.example.cixoil.enums.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IncidentSaveDTO(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no debe superar los 150 caracteres")
        String title,

        @NotNull(message = "El tipo de incidente es obligatorio")
        Long idIncidentType,

        @NotNull(message = "La prioridad es obligatoria")
        Priority priority,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 1000, message = "La descripción no debe superar los 1000 caracteres")
        String description,

        @NotBlank(message = "El campo 'reportado por' es obligatorio")
        @Size(max = 100, message = "El campo 'reportado por' no debe superar los 100 caracteres")
        String reportedBy,

        Long idIncidentCategory,

        @Size(max = 255, message = "La referencia no debe superar los 255 caracteres")
        String reference,

        @Size(max = 1000, message = "La nota de resolución no debe superar los 1000 caracteres")
        String resolutionNote
) {
}
