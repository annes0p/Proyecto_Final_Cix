package com.example.cixoil.dto.module;

import java.util.List;

public record PermissionDTO(
        String role,
        List<ModuleDTO> modules
) {
}
