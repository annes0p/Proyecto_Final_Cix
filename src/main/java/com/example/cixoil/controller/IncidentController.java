package com.example.cixoil.controller;

import com.example.cixoil.dto.incident.IncidentResolveRequestDTO;
import com.example.cixoil.dto.incident.IncidentSaveDTO;
import com.example.cixoil.service.IncidentService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseUtil.ok(
                "Incidentes obtenidos correctamente",
                incidentService.listAll()
        );
    }

    @GetMapping("/list/open")
    public ResponseEntity<?> listOpen() {
        return ResponseUtil.ok(
                "Incidentes abiertos obtenidos correctamente",
                incidentService.listOpen()
        );
    }

    @GetMapping("/list/not-canceled")
    public ResponseEntity<?> listNotCanceled() {
        return ResponseUtil.ok(
                "Incidentes no cancelados obtenidos correctamente",
                incidentService.listNotCanceled()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente obtenido correctamente",
                incidentService.getById(id)
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody IncidentSaveDTO dto) {
        return ResponseUtil.ok(
                "Incidente creado correctamente",
                incidentService.create(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody IncidentSaveDTO dto, @PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente actualizado correctamente",
                incidentService.update(dto, id)
        );
    }

    @PatchMapping("/{id}/in-process")
    public ResponseEntity<?> inProcess(@PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente ha sido marcado como 'En proceso' correctamente",
                incidentService.inProcess(id)
        );
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<?> resolve(@RequestBody IncidentResolveRequestDTO dto, @PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente resuelto correctamente",
                incidentService.resolve(id, dto)
        );
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<?> close(@PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente resuelto correctamente",
                incidentService.close(id)
        );
    }

    @PatchMapping("/{id}/reopen")
    public ResponseEntity<?> reopen(@PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente resuelto correctamente",
                incidentService.reopen(id)
        );
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        return ResponseUtil.ok(
                "Incidente resuelto correctamente",
                incidentService.cancel(id)
        );
    }

    @PatchMapping("/{id}/next-state")
    public ResponseEntity<?> next(@PathVariable Long id) {
        return ResponseUtil.ok(
                "El incidente ha avanzado al siguiente estado correctamente",
                incidentService.next(id)
        );
    }
}
