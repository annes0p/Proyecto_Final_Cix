package com.example.cixoil.dto.supplier;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Reemplaza la lista completa de productos que ofrece un proveedor
 * (relacion supplier_product). Se manda la lista completa de ids en vez
 * de un add/remove individual porque la UI es un checklist: mas simple
 * de razonar y de implementar que sincronizar altas/bajas una por una.
 */
public record SupplierProductsSaveDTO(
        @NotNull List<Long> productIds
) {
}
