package com.example.cixoil.dto.publicsale;

/**
 * Resultado normalizado de la consulta a apiperu.dev (DNI = padron
 * reducido SUNAT, no RENIEC oficial; RUC = SUNAT). No todas las
 * consultas devuelven resultado (cobertura parcial de la fuente), eso
 * es esperado y no significa que el documento sea invalido.
 */
public record PublicDocumentLookupDTO(
        boolean found,
        String name,
        String fatherLastName,
        String motherLastName,
        String rucEstado,
        String rucCondicion
) {
}
