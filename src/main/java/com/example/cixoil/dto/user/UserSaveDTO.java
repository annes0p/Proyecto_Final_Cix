package com.example.cixoil.dto.user;

public record UserSaveDTO(
        Long id,
        String username,
        String email,
        String password,
        Long roleId
) {
}
