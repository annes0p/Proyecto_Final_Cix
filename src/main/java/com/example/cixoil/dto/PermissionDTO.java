package com.example.cixoil.dto;

import java.util.List;

public record PermissionDTO(
        String role,
        List<ModuleDTO> modules
) {
}
