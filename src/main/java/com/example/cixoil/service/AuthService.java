package com.example.cixoil.service;

import com.example.cixoil.dto.auth.*;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceDisabledException;
import com.example.cixoil.exception.WrongPasswordException;
import com.example.cixoil.mapper.UserMapper;
import com.example.cixoil.model.User;
import com.example.cixoil.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userService.requireByUsernameOrEmail(request.identifier());

        if (!userService.validatePassword(request.password(), user.getPassword()))
            throw new WrongPasswordException("Contraseña incorrecta");

        if (!Objects.equals(user.getStatus(), Status.ACTIVE.getValue()))
            throw new ResourceDisabledException("Usuario inactivo");
        //TODO: Cambiar los mensajes de error para evitar vulnerabilidades

        AuthUserDTO authUserDTO = userMapper.toAuthUserDTO(user);

        String accessToken = jwtService.generateAccessToken(authUserDTO);
        String refreshToken = jwtService.generateRefreshToken(authUserDTO);

        return new LoginResponseDTO(
                new AuthDTO(
                        accessToken,
                        refreshToken,
                        "Bearer",
                        jwtService.getExpiration()
                ),
                authUserDTO
        );
    }

    public RefreshTokenResponseDTO refresh(RefreshTokenRequestDTO request) {
        String refreshToken = request.refreshToken();

        String userId = jwtService.extractUserId(refreshToken);

        AuthUserDTO authUserDTO = userService.getAuthDTOById(Long.valueOf(userId)).orElseThrow();

        if (!jwtService.isRefreshTokenValid(refreshToken, authUserDTO))
            throw new InvalidArgumentException("Refresh token inválido");

        return new RefreshTokenResponseDTO(
                jwtService.generateAccessToken(authUserDTO),
                "Bearer",
                jwtService.getExpiration()
        );
    }
}
