package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.inventory.InventoryDTO;
import com.example.cixoil.dto.inventory.InventorySaveDTO;
import com.example.cixoil.service.InventoryService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<InventoryDTO> data = inventoryService.listAllForNotDeletedProducts();
        return ResponseUtil.ok("Inventarios encontrados exitosamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        InventoryDTO data = inventoryService.findById(id);
        return ResponseUtil.ok("Inventario encontrado exitosamente", data);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findByProductId(@PathVariable Long id) {
        InventoryDTO data = inventoryService.findByProductId(id);
        return ResponseUtil.ok("Inventario de producto encontrado exitosamente", data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody InventorySaveDTO dto) {
        InventoryDTO data = inventoryService.create(dto);
        return ResponseUtil.ok("Inventario de producto encontrado exitosamente", data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody InventorySaveDTO dto, @PathVariable Long id) {
        InventoryDTO data = inventoryService.update(dto, id);
        return ResponseUtil.ok("Inventario de producto encontrado exitosamente", data);
    }
}
