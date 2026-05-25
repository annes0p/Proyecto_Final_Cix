package com.example.cixoil.controller;

import com.example.cixoil.dto.user.UserDTO;
import com.example.cixoil.dto.user.UserSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.UserService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> listNotDeletedUsers() {
        List<UserDTO> data = userService.findNotDeleted();
        return ResponseUtil.ok("Usuarios obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        UserDTO existent = userService.getById(id);
        return  ResponseUtil.ok("Usuario encontrado", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserSaveDTO dto) {
        UserDTO created = userService.create(dto);
        return ResponseUtil.ok("Usuario creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserSaveDTO dto, @PathVariable Long id) {
        UserDTO updated = userService.update(id, dto);
        return ResponseUtil.ok("Usuario actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        UserDTO toggled = userService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Usuario activado correctamente" : "Usuario desactivado correctamente";
        return ResponseUtil.ok(
                msg,
                toggled
        );
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseUtil.ok("Usuario eliminado correctamente", null);
    }

}
