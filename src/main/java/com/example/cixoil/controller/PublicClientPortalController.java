package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.publicsale.PublicClientIncidentDTO;
import com.example.cixoil.dto.publicsale.PublicClientOrderDTO;
import com.example.cixoil.dto.publicsale.PublicIncidentReportRequestDTO;
import com.example.cixoil.service.PublicClientPortalService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Portal del cliente (sin login, identificado por DNI/RUC): sus pedidos
 * con seguimiento en vivo y sus incidencias, mas la posibilidad de
 * reportar una incidencia nueva. Ver PublicClientPortalService.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/clients")
public class PublicClientPortalController {

    private final PublicClientPortalService publicClientPortalService;

    @GetMapping("/{docNumber}/orders")
    public ResponseEntity<?> getPedidos(@PathVariable String docNumber) {
        List<PublicClientOrderDTO> data = publicClientPortalService.buscarPedidos(docNumber);
        return ResponseUtil.ok("Pedidos obtenidos correctamente", data);
    }

    @GetMapping("/{docNumber}/incidents")
    public ResponseEntity<?> getIncidencias(@PathVariable String docNumber) {
        List<PublicClientIncidentDTO> data = publicClientPortalService.buscarIncidencias(docNumber);
        return ResponseUtil.ok("Incidencias obtenidas correctamente", data);
    }

    @PostMapping("/incidents")
    public ResponseEntity<?> reportarIncidencia(@Valid @RequestBody PublicIncidentReportRequestDTO dto) {
        PublicClientIncidentDTO created = publicClientPortalService.reportarIncidencia(dto);
        return ResponseUtil.ok("Incidencia reportada correctamente", created);
    }
}
