package com.example.cixoil.dto;

public record SelectDTO<T>(
        T value,
        String label
) {
}
