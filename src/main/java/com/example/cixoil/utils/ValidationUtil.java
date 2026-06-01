package com.example.cixoil.utils;

import com.example.cixoil.exception.InvalidArgumentException;

public final class ValidationUtil {

    public static void validateId(Long id, String entityName) {
        if (id == null) throw new InvalidArgumentException("ID de " + entityName + " es null");
        if (id <= 0) throw new InvalidArgumentException("ID de " + entityName + " inválido");
    }

    public static void validateQuantity(Long quantity) {
        if (quantity == null) throw new InvalidArgumentException("La cantidad no puede ser null");
        if (quantity < 0) throw new InvalidArgumentException("La cantidad no puede ser menor a 0");
    }
}
