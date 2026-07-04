package com.example.cixoil.dto.publicsale;

/**
 * Resultado de buscar si ya existe un cliente registrado por
 * docNumber en NUESTRA base (no la fuente externa). Se usa para que
 * un cliente recurrente no tenga que reescribir sus datos cada vez
 * que compra o reporta una incidencia.
 */
public record PublicClientLookupDTO(
        boolean found,
        String name,
        String fatherLastName,
        String motherLastName,
        String documentType,
        String phoneNumber,
        String email,
        String address
) {
}
