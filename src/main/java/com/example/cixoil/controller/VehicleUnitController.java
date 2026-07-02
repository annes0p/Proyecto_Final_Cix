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

import com.example.cixoil.dto.vehicleunit.VehicleUnitDTO;
import com.example.cixoil.dto.vehicleunit.VehicleUnitSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.VehicleUnitService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicles/units")
public class VehicleUnitController {
    
    private final VehicleUnitService vehicleUnitService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<VehicleUnitDTO> data = vehicleUnitService.findNotDeleted();
        return ResponseUtil.ok("Unidades vehiculares obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        VehicleUnitDTO existent = vehicleUnitService.getById(id);
        return ResponseUtil.ok("Unidad vehicular obtenida correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VehicleUnitSaveDTO dto) {
        VehicleUnitDTO created = vehicleUnitService.create(dto);
        return ResponseUtil.ok("Unidad vehicular creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody VehicleUnitSaveDTO dto, @PathVariable Long id) {
        VehicleUnitDTO updated = vehicleUnitService.update(dto, id);
        return ResponseUtil.ok("Unidad vehicular actualizada correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        VehicleUnitDTO toggled = vehicleUnitService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Unidad vehicular activada correctamente" : "Unidad vehicular desactivada correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vehicleUnitService.delete(id);
        return ResponseUtil.ok("Unidad vehicular eliminada correctamente", null);
    }
}
