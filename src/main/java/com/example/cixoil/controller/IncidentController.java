package com.example.cixoil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.incident.IncidentRatingSaveDTO;
import com.example.cixoil.dto.incident.IncidentResolveRequestDTO;
import com.example.cixoil.dto.incident.IncidentSaveDTO;
import com.example.cixoil.service.IncidentService;
import com.example.cixoil.utils.ResponseUtil;

import java.util.Map;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<?> create(@Valid @RequestBody IncidentSaveDTO dto) {
        return ResponseUtil.ok(
                "Incidente creado correctamente",
                incidentService.create(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody IncidentSaveDTO dto, @PathVariable Long id) {
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
    public ResponseEntity<?> resolve(@Valid @RequestBody IncidentResolveRequestDTO dto, @PathVariable Long id) {
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

    /**
     * Autenticado: el vendedor pide el link para mandarselo al cliente
     * por WhatsApp y que califique como se resolvio su incidencia.
     */
    @GetMapping("/{id}/rating-link")
    public ResponseEntity<?> generarLinkCalificacion(@PathVariable Long id) {
        String token = incidentService.generarTokenCalificacion(id);
        return ResponseUtil.ok("Token de calificación generado", Map.of("token", token));
    }

    /**
     * Publico: el cliente abre el link y ve que se resolvio, sin login.
     */
    @GetMapping("/public/{token}/rating")
    public ResponseEntity<?> verParaCalificar(@PathVariable String token) {
        return ResponseUtil.ok(
                "Incidente encontrado",
                incidentService.buscarParaCalificar(token)
        );
    }

    /**
     * Publico: el cliente envia su calificacion (1 a 5), sin login.
     */
    @PatchMapping("/public/{token}/rating")
    public ResponseEntity<?> calificar(
            @PathVariable String token,
            @Valid @RequestBody IncidentRatingSaveDTO dto
    ) {
        return ResponseUtil.ok(
                "Gracias por tu calificación",
                incidentService.calificar(token, dto)
        );
    }
}
