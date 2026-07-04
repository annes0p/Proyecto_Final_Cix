package com.example.cixoil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.publicsale.PublicDocumentLookupDTO;
import com.example.cixoil.service.DocumentLookupService;
import com.example.cixoil.utils.ResponseUtil;

import lombok.RequiredArgsConstructor;

/**
 * Consulta publica de DNI/RUC (apiperu.dev) para autocompletar nombre
 * en el portal del cliente. Ver DocumentLookupService para el detalle
 * de por que "no encontrado" es un resultado normal, no un error.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/document-lookup")
public class PublicDocumentLookupController {

    private final DocumentLookupService documentLookupService;

    @GetMapping("/{tipo}/{numero}")
    public ResponseEntity<?> buscar(@PathVariable String tipo, @PathVariable String numero) {
        PublicDocumentLookupDTO data = "RUC".equalsIgnoreCase(tipo)
                ? documentLookupService.buscarRuc(numero)
                : documentLookupService.buscarDni(numero);
        return ResponseUtil.ok("Consulta realizada", data);
    }
}
