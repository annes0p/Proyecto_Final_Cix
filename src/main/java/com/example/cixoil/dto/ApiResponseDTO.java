package com.example.cixoil.dto;

public record ApiResponseDTO<T>(
    boolean success,
    String message,
    T data
) {
}
