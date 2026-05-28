package com.example.cixoil.controller;

import com.example.cixoil.dto.trip.TripDTO;
import com.example.cixoil.dto.trip.TripSaveDTO;
import com.example.cixoil.dto.trip.TripTimeSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.TripService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<TripDTO> data = tripService.findNotDeleted();
        return ResponseUtil.ok("Viajes obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        TripDTO existent = tripService.getById(id);
        return ResponseUtil.ok("Viaje encontrado correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TripSaveDTO dto) {
        TripDTO created = tripService.create(dto);
        return ResponseUtil.ok("Viaje creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TripSaveDTO dto, @PathVariable Long id) {
        TripDTO updated = tripService.update(dto, id);
        return ResponseUtil.ok("Viaje actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/times")
    public ResponseEntity<?> updateTimes(@RequestBody TripTimeSaveDTO dto, @PathVariable Long id) {
        TripDTO updated = tripService.updateTimes(dto, id);
        return ResponseUtil.ok("Horas actualizadas correctamente", updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        TripDTO toggled = tripService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Viaje activado correctamente" : "Viaje desactivado correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tripService.delete(id);
        return ResponseUtil.ok("Producto eliminado correctamente");
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<?> start(@PathVariable Long id) {
        TripDTO started = tripService.start(id);
        return ResponseUtil.ok("Viaje iniciado correctamente", started);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id) {
        TripDTO completed = tripService.complete(id);
        return ResponseUtil.ok("Viaje completado correctamente", completed);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        TripDTO cancelled = tripService.cancel(id);
        return ResponseUtil.ok("Viaje completado correctamente", cancelled);
    }

    @PatchMapping("/{id}/resume")
    public ResponseEntity<?> resume(@PathVariable Long id) {
        TripDTO resumed = tripService.resume(id);
        return ResponseUtil.ok("Viaje completado correctamente", resumed);
    }

}
