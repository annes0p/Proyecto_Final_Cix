package com.example.cixoil.controller;

import com.example.cixoil.dto.vehiclemodel.VehicleModelDTO;
import com.example.cixoil.dto.vehiclemodel.VehicleModelSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.VehicleModelService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicles/models")
public class VehicleModelController {
    
    private final VehicleModelService vehicleModelService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<VehicleModelDTO> data = vehicleModelService.findNotDeleted();
        return ResponseUtil.ok("Modelos obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        VehicleModelDTO existent = vehicleModelService.getById(id);
        return ResponseUtil.ok("Modelo obtenidos correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VehicleModelSaveDTO dto) {
        VehicleModelDTO created = vehicleModelService.create(dto);
        return ResponseUtil.ok("Modelo creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody VehicleModelSaveDTO dto, @PathVariable Long id) {
        VehicleModelDTO updated = vehicleModelService.update(dto, id);
        return ResponseUtil.ok("Modelo actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        VehicleModelDTO toggled = vehicleModelService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Modelo activado correctamente" : "Modelo desactivado correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vehicleModelService.delete(id);
        return ResponseUtil.ok("Modelo eliminado correctamente", null);
    }
    
}
