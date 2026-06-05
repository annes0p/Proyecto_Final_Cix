package com.example.cixoil.controller;

import com.example.cixoil.dto.sale.SaleDTO;
import com.example.cixoil.dto.sale.SaleSaveDTO;
import com.example.cixoil.service.SaleService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> create(@RequestBody SaleSaveDTO dto) {
        SaleDTO created = saleService.create(dto);
        return ResponseUtil.ok("Venta creada correctamente", created);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        SaleDTO canceled = saleService.cancel(id);
        return ResponseUtil.ok("Venta cancelada", canceled);
    }
}
