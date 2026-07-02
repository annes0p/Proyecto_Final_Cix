package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.stockmovement.StockMovementDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.service.StockMovementService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movements")
public class StockMovementController {
    
    private final StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<StockMovementDTO> data = stockMovementService.listAll();
        return ResponseUtil.ok("Movimientos encontrados exitosamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        StockMovementDTO data = stockMovementService.findById(id);
        return ResponseUtil.ok("Movimiento encontrado exitosamente", data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StockMovementSaveDTO dto) {
        StockMovementDTO data = stockMovementService.create(dto);
        return ResponseUtil.ok("Movimiento de producto encontrado exitosamente", data);
    }
}
