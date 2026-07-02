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

import com.example.cixoil.dto.route.RouteDTO;
import com.example.cixoil.dto.route.RouteSaveDTO;
import com.example.cixoil.dto.trip.TripDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.RouteService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<RouteDTO> data = routeService.findNotDeleted();
        return ResponseUtil.ok("Rutas obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        RouteDTO existent = routeService.getById(id);
        return ResponseUtil.ok("Ruta obtenidas correctamente", existent);
    }

    @GetMapping("/{id}/trips")
    public ResponseEntity<?> findTripsById(@PathVariable Long id) {
        List<TripDTO> data = routeService.findTripsByRouteIdNotDeleted(id);
        return ResponseUtil.ok("Viajes de la ruta obtenidos correctamente", data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RouteSaveDTO dto) {
        RouteDTO created = routeService.create(dto);
        return ResponseUtil.ok("Ruta creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RouteSaveDTO dto, @PathVariable Long id) {
        RouteDTO updated = routeService.update(dto, id);
        return ResponseUtil.ok("Ruta actualizada correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        RouteDTO toggled = routeService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Ruta activada correctamente" : "Ruta desactivada correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        routeService.delete(id);
        return ResponseUtil.ok("Ruta eliminada correctamente", null);
    }

}
