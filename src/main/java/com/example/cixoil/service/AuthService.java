package com.example.cixoil.service;

import com.example.cixoil.dto.*;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceDisabledException;
import com.example.cixoil.exception.WrongPasswordException;
import com.example.cixoil.mapper.UserMapper;
import com.example.cixoil.model.User;
import com.example.cixoil.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        if (user.getStatus() != Status.ACTIVE.getValue())
            throw new ResourceDisabledException("Credenciales inválidas");

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(
                new AuthDTO(
                        accessToken,
                        refreshToken,
                        "Bearer",
                        jwtService.getExpiration()
                ),
                userMapper.toAuthUserDTO(user)
        );
    }
}
