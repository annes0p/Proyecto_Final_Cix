package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.module.ModuleDTO;
import com.example.cixoil.dto.role.RoleDTO;
import com.example.cixoil.dto.role.RoleSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.RoleService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {
    
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<RoleDTO> data = roleService.findNotDeleted();
        return ResponseUtil.ok("Roles obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        RoleDTO existent = roleService.getById(id);
        return ResponseUtil.ok("Rol obtenido correctamente", existent);
    }

    @GetMapping("/{id}/modules")
    public ResponseEntity<?> findModulesById(@PathVariable Long id) {
        List<ModuleDTO> data = roleService.findModulesByRouteId(id);
        return ResponseUtil.ok("Módulos del rol obtenidos correctamente", data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleSaveDTO dto) {
        RoleDTO created = roleService.create(dto);
        return ResponseUtil.ok("Rol creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RoleSaveDTO dto, @PathVariable Long id) {
        RoleDTO updated = roleService.update(dto, id);
        return ResponseUtil.ok("Rol actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        RoleDTO toggled = roleService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Rol activado correctamente" : "Rol desactivado correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseUtil.ok("Rol eliminado correctamente", null);
    }
    
}
