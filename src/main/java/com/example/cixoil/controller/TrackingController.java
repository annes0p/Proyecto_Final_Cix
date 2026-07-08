package com.example.cixoil.controller;

import com.example.cixoil.dto.auth.AuthUserDTO;
import com.example.cixoil.dto.trip.PublicTrackingDTO;
import com.example.cixoil.dto.trip.TripLocationSaveDTO;
import com.example.cixoil.dto.trip.TripMessageDTO;
import com.example.cixoil.dto.trip.TripMessageSaveDTO;
import com.example.cixoil.dto.trip.TripRatingSaveDTO;
import com.example.cixoil.service.TrackingService;
import com.example.cixoil.utils.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    /**
     * Publico: el cliente califica como llego su entrega (1 a 5), sin login,
     * usando el mismo token de seguimiento. Solo funciona si el viaje ya
     * esta COMPLETED.
     */
    @PatchMapping("/public/{token}/rating")
    public ResponseEntity<?> calificarEntrega(
            @PathVariable String token,
            @Valid @RequestBody TripRatingSaveDTO dto
    ) {
        PublicTrackingDTO data = trackingService.calificarEntrega(token, dto.rating());
        return ResponseUtil.ok("Gracias por calificar tu entrega", data);
    }

    /**
     * Chat (no chatbot) entre cliente y personal sobre un envio puntual.
     * Lado publico: el cliente lee y escribe usando el mismo token de
     * seguimiento, sin login.
     */
    @GetMapping("/public/{token}/messages")
    public ResponseEntity<?> listarMensajesPublico(@PathVariable String token) {
        List<TripMessageDTO> mensajes = trackingService.listarMensajesPorToken(token);
        return ResponseUtil.ok("Mensajes encontrados", mensajes);
    }

    @PostMapping("/public/{token}/messages")
    public ResponseEntity<?> enviarMensajePublico(
            @PathVariable String token,
            @Valid @RequestBody TripMessageSaveDTO dto
    ) {
        TripMessageDTO mensaje = trackingService.enviarMensajeCliente(token, dto.content());
        return ResponseUtil.ok("Mensaje enviado", mensaje);
    }

    /**
     * Lado personal: autenticado, responde desde el detalle de la ruta.
     */
    @GetMapping("/{idTrip}/messages")
    public ResponseEntity<?> listarMensajesStaff(@PathVariable Long idTrip) {
        List<TripMessageDTO> mensajes = trackingService.listarMensajesPorTripId(idTrip);
        return ResponseUtil.ok("Mensajes encontrados", mensajes);
    }

    @PostMapping("/{idTrip}/messages")
    public ResponseEntity<?> enviarMensajeStaff(
            @PathVariable Long idTrip,
            @Valid @RequestBody TripMessageSaveDTO dto,
            @AuthenticationPrincipal AuthUserDTO authUserDTO
    ) {
        TripMessageDTO mensaje = trackingService.enviarMensajeStaff(
                idTrip, dto.content(), authUserDTO != null ? authUserDTO.username() : null);
        return ResponseUtil.ok("Mensaje enviado", mensaje);
    }
}
