package com.example.cixoil.dto.user;

public record UserDTO(
        Long id,
        String username,
        String email,
        Integer status,
        String role
) {
}
