package com.example.cixoil.controller;

import com.example.cixoil.dto.UserDTO;
import com.example.cixoil.service.UserService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> listActiveUsers() {
        List<UserDTO> data = userService.findActives();
        return ResponseUtil.ok("Usuarios obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> ResponseUtil.ok("Usuario encontrado", user))
                .orElseGet(() -> ResponseUtil.notFound("Usuario no encontrado"));
    }

}
