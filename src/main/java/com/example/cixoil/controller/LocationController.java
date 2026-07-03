package com.example.cixoil.controller;

import com.example.cixoil.dto.location.LocationDTO;
import com.example.cixoil.dto.location.LocationSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.LocationService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<LocationDTO> data = locationService.findAllNotDeleted();
        return ResponseUtil.ok("Lugares encontrados", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        LocationDTO existent = locationService.getById(id);
        return ResponseUtil.ok("Lugar obtenido correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LocationSaveDTO dto) {
        return ResponseUtil.ok(
                "Localización creada correctamente",
                locationService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody LocationSaveDTO dto, @PathVariable Long id) {
        return ResponseUtil.ok(
                "Localización actualizada correctamente",
                locationService.update(dto, id));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        LocationDTO data = locationService.toggle(id);
        String message = data.status().equals(Status.ACTIVE.getValue()) ?
                "Localización activada correctamente" : "Localización desactivada correctamente";

        return ResponseUtil.ok(message, data);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseUtil.ok("Localizaicón eliminada exitosamente");
    }
}
