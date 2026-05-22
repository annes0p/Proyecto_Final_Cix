package com.example.cixoil.controller;

import com.example.cixoil.dto.auth.LoginRequestDTO;
import com.example.cixoil.dto.auth.LoginResponseDTO;
import com.example.cixoil.dto.auth.RefreshTokenRequestDTO;
import com.example.cixoil.dto.auth.RefreshTokenResponseDTO;
import com.example.cixoil.service.AuthService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO data = authService.login(request);
        return ResponseUtil.ok("Autenticación exitosa", data);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequestDTO request) {
        RefreshTokenResponseDTO data = authService.refresh(request);
        return ResponseUtil.ok("Token regenerado exitosamente", data);
    }
}
