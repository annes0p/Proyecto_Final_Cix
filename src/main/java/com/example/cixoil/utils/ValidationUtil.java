package com.example.cixoil.utils;

import com.example.cixoil.exception.InvalidArgumentException;

public final class ValidationUtil {

    public static void validateId(Long id, String entityName) {
        if (id == null) throw new InvalidArgumentException("ID de " + entityName + " es null");
        if (id <= 0) throw new InvalidArgumentException("ID de " + entityName + " inválido");
    }
}
