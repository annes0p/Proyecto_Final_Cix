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

import com.example.cixoil.dto.purchase.PartialReceiveDTO;
import com.example.cixoil.dto.purchase.PurchaseDTO;
import com.example.cixoil.dto.purchase.PurchaseSaveDTO;
import com.example.cixoil.service.PurchaseService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<?> create(@Valid @RequestBody PurchaseSaveDTO dto) {
        PurchaseDTO created = purchaseService.create(dto);
        return ResponseUtil.ok("Compra creada correctamente", created);
    }

    @PatchMapping("/{id}/receive")
    public ResponseEntity<?> receive(@PathVariable Long id) {
        PurchaseDTO data = purchaseService.receive(id);
        return ResponseUtil.ok("Compra recibida", data);
    }

    @PatchMapping("/{id}/partially-receive")
    public ResponseEntity<?> partiallyReceive(
            @PathVariable Long id, 
            @Valid @RequestBody PartialReceiveDTO dto
    ) {
        PurchaseDTO data = purchaseService.partiallyReceive(id, dto); 
        return ResponseUtil.ok("Compra parcialmente recibida", data);
    }
}
