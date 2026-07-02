package com.example.cixoil.dto.incident;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record IncidentResolveRequestDTO(
        @NotBlank(message = "La nota de resolución es obligatoria")
        @Size(max = 1000, message = "La nota de resolución no debe superar los 1000 caracteres")
        String resolutionNote
) {
}
