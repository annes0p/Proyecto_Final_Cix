package com.example.cixoil.dto.client;

import com.example.cixoil.enums.DocumentType;

public record ClientSaveDTO(
        String name,
        String fatherLastName,
        String motherLastName,
        DocumentType documentType,
        String docNumber,
        String phoneNumber,
        String email,
        Long idLocation,
        String address,
        boolean trusted
) {
}
