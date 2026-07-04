package com.example.cixoil.dto.incident;

/**
 * DTO publico para la pantalla donde el cliente califica como se
 * resolvio su incidencia, sin necesidad de login.
 */
public record PublicIncidentRatingDTO(
        Long id,
        String titulo,
        String resolutionNote,
        Integer rating
) {
}
