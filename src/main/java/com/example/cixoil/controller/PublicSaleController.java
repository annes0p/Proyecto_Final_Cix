package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.publicsale.PublicProductDTO;
import com.example.cixoil.dto.publicsale.PublicSaleRequestDTO;
import com.example.cixoil.dto.publicsale.PublicSaleResponseDTO;
import com.example.cixoil.service.PublicSaleService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Portal público de venta (sin login): catálogo básico + registro de un
 * pedido pendiente para clientes no recurrentes. Ver PublicSaleService
 * para el detalle de por qué está aislado del flujo interno de ventas.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicSaleController {

    private final PublicSaleService publicSaleService;

    @GetMapping("/catalog")
    public ResponseEntity<?> getCatalogo() {
        List<PublicProductDTO> data = publicSaleService.getCatalogo();
        return ResponseUtil.ok("Catálogo obtenido correctamente", data);
    }

    @PostMapping("/sales")
    public ResponseEntity<?> crearVenta(@Valid @RequestBody PublicSaleRequestDTO dto) {
        PublicSaleResponseDTO created = publicSaleService.crearVentaPublica(dto);
        return ResponseUtil.ok("Pedido registrado correctamente", created);
    }
}
