package com.example.cixoil.dto.supplier;

import com.example.cixoil.enums.DocumentType;

public record SupplierDTO(
        Long id,
        String legalName,
        DocumentType documentType,
        String docNumber,
        String phoneNumber,
        String email,
        String address,
        Integer status
) {
}
