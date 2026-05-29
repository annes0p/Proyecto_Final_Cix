package com.example.cixoil.dto.client;

import com.example.cixoil.model.Location;

public record ClientDTO(
        Long id,
        String name,
        String fatherLastName,
        String motherLastName,
        String documentType,
        String docNumber,
        String phoneNumber,
        String email,
        Location location,
        String address,
        Integer status,
        boolean trusted
) {
}
