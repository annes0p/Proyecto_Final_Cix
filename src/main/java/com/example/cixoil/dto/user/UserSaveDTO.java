package com.example.cixoil.dto.user;

public record UserSaveDTO(
        String username,
        String email,
        String password,
        Long roleId
) {
}
