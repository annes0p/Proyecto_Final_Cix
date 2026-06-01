package com.example.cixoil.controller;

import com.example.cixoil.dto.inventory.InventoryDTO;
import com.example.cixoil.dto.inventory.InventorySaveDTO;
import com.example.cixoil.service.InventoryService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<InventoryDTO> data = inventoryService.listAll();
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
    public ResponseEntity<?> create(@RequestBody InventorySaveDTO dto) {
        InventoryDTO data = inventoryService.create(dto);
        return ResponseUtil.ok("Inventario de producto encontrado exitosamente", data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody InventorySaveDTO dto, @PathVariable Long id) {
        InventoryDTO data = inventoryService.update(dto, id);
        return ResponseUtil.ok("Inventario de producto encontrado exitosamente", data);
    }
}
