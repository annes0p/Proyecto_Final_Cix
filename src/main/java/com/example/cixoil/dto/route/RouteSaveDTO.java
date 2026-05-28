package com.example.cixoil.dto.route;

import java.time.LocalDate;

public record RouteSaveDTO(
        Long idUser,
        LocalDate routeDate
) {
}
