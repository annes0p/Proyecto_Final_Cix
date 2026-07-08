package com.example.cixoil.dto.trip;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TripMessageSaveDTO(
        @NotBlank(message = "El mensaje no puede estar vacío")
        @Size(max = 500, message = "El mensaje es muy largo")
        String content
) {
}
