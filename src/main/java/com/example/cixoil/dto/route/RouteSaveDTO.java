package com.example.cixoil.dto.route;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record RouteSaveDTO(
        @NotNull(message = "El usuario es obligatorio")
        Long idUser,

        @NotNull(message = "La fecha de ruta es obligatoria")
        LocalDate routeDate
) {
}
