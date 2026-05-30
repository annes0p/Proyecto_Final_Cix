package com.example.cixoil.dto.role;

import java.util.List;

public record RoleSaveDTO(
        String name,
        String description,
        List<Long> idModules
) {
}
