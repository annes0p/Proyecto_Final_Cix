package com.example.cixoil.controller;

import com.example.cixoil.dto.ApiResponseDTO;
import com.example.cixoil.dto.LoginRequestDTO;
import com.example.cixoil.dto.LoginResponseDTO;
import com.example.cixoil.service.AuthService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
