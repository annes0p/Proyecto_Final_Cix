package com.example.cixoil.utils;

import com.example.cixoil.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtil {

    public static <T> ResponseEntity<ApiResponseDTO<T>> ok(String msg, T data) {
        return ResponseEntity.ok(new ApiResponseDTO<>(true, msg, data));
    }

    public static <T> ResponseEntity<ApiResponseDTO<T>> ok(String msg) {
        return ResponseEntity.ok(new ApiResponseDTO<>(true, msg, null));
    }

    public static <T> ResponseEntity<ApiResponseDTO<T>> notFound(String msg) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>(false, msg, null));
    }
}
