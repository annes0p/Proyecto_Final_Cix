package com.example.cixoil.controller;

import com.example.cixoil.dto.trip.PublicTrackingDTO;
import com.example.cixoil.dto.trip.TripLocationSaveDTO;
import com.example.cixoil.service.TrackingService;
import com.example.cixoil.utils.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    /**
     * Endpoint autenticado: el vendedor pide el link para mandarselo al
     * cliente por WhatsApp.
     */
    @GetMapping("/{idTrip}/link")
    public ResponseEntity<?> generarLink(@PathVariable Long idTrip) {
        String token = trackingService.generarToken(idTrip);
        return ResponseUtil.ok("Token de seguimiento generado", Map.of("token", token));
    }

    /**
     * Endpoint publico: lo abre el cliente desde el link, sin necesidad de
     * iniciar sesion.
     */
    @GetMapping("/public/{token}")
    public ResponseEntity<?> seguimientoPublico(@PathVariable String token) {
        PublicTrackingDTO data = trackingService.buscarPorToken(token);
        return ResponseUtil.ok("Seguimiento encontrado", data);
    }

    /**
     * Endpoint autenticado: el navegador/celular del vendedor manda su
     * posicion GPS mientras el viaje esta en curso.
     */
    @PatchMapping("/{idTrip}/location")
    public ResponseEntity<?> actualizarUbicacion(
            @PathVariable Long idTrip,
            @Valid @RequestBody TripLocationSaveDTO dto
    ) {
        trackingService.actualizarUbicacion(idTrip, dto.latitude(), dto.longitude());
        return ResponseUtil.ok("Ubicación actualizada correctamente");
    }
}
