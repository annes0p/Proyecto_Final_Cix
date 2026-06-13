package com.example.cixoil.dto.client;

import com.example.cixoil.dto.location.LocationDTO;

public record ClientDTO(
        Long id,
        String name,
        String fatherLastName,
        String motherLastName,
        String documentType,
        String docNumber,
        String phoneNumber,
        String email,
        LocationDTO location,
        String address,
        Integer status,
        boolean trusted
) {
}
