package com.example.cixoil.dto.role;

import com.example.cixoil.dto.module.ModuleDTO;

import java.util.List;

public record RoleDTO(
        Long id,
        String name,
        String description,
        List<ModuleDTO> modules,
        Integer status
) {
}
