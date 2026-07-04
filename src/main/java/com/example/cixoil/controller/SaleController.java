package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.sale.SaleDTO;
import com.example.cixoil.dto.sale.SalePaymentConfirmDTO;
import com.example.cixoil.dto.sale.SaleSaveDTO;
import com.example.cixoil.service.SaleService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<SaleDTO> data = saleService.findAll();
        return ResponseUtil.ok("Ventas obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        SaleDTO existent = saleService.getById(id);
        return ResponseUtil.ok("Venta obtenida correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SaleSaveDTO dto) {
        SaleDTO created = saleService.create(dto);
        return ResponseUtil.ok("Venta creada correctamente", created);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        SaleDTO canceled = saleService.cancel(id);
        return ResponseUtil.ok("Venta cancelada", canceled);
    }

    /**
     * Confirma el pago de una venta pendiente (por ejemplo, un pedido del
     * portal publico). Paso previo antes de crear el envio/ruta.
     */
    @PatchMapping("/{id}/confirm-payment")
    public ResponseEntity<?> confirmarPago(
            @PathVariable Long id,
            @Valid @RequestBody SalePaymentConfirmDTO dto
    ) {
        SaleDTO confirmada = saleService.confirmarPago(id, dto.paymentMethod());
        return ResponseUtil.ok("Pago confirmado correctamente", confirmada);
    }
}
