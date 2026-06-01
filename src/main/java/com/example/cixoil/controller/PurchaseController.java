package com.example.cixoil.controller;

import com.example.cixoil.dto.purchase.PurchaseDTO;
import com.example.cixoil.dto.purchase.PurchaseSaveDTO;
import com.example.cixoil.service.PurchaseService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<PurchaseDTO> data = purchaseService.findAll();
        return ResponseUtil.ok("Compras obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PurchaseDTO existent = purchaseService.getById(id);
        return ResponseUtil.ok("Compra obtenida correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PurchaseSaveDTO dto) {
        PurchaseDTO created = purchaseService.create(dto);
        return ResponseUtil.ok("Compra creada correctamente", created);
    }
}
