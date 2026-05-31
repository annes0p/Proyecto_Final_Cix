package com.example.cixoil.controller;

import com.example.cixoil.dto.vehicleunit.VehicleUnitDTO;
import com.example.cixoil.dto.vehicleunit.VehicleUnitSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.VehicleUnitService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> create(@RequestBody VehicleUnitSaveDTO dto) {
        VehicleUnitDTO created = vehicleUnitService.create(dto);
        return ResponseUtil.ok("Unidad vehicular creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody VehicleUnitSaveDTO dto, @PathVariable Long id) {
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
