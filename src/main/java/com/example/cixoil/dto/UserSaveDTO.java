package com.example.cixoil.dto;

public record UserSaveDTO(
        Long id,
        String username,
        String email,
        String password,
        Long roleId
) {
}
