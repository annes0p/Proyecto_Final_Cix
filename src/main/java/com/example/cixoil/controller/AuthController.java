package com.example.cixoil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.auth.LoginRequestDTO;
import com.example.cixoil.dto.auth.LoginResponseDTO;
import com.example.cixoil.dto.auth.RefreshTokenRequestDTO;
import com.example.cixoil.dto.auth.RefreshTokenResponseDTO;
import com.example.cixoil.service.AuthService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO data = authService.login(request);
        return ResponseUtil.ok("Autenticación exitosa", data);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody RefreshTokenRequestDTO request) {
        RefreshTokenResponseDTO data = authService.refresh(request);
        return ResponseUtil.ok("Token regenerado exitosamente", data);
    }
}
