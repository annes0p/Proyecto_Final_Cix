package com.example.cixoil.controller;

import com.example.cixoil.dto.stockmovement.StockMovementDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.service.StockMovementService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> create(@RequestBody StockMovementSaveDTO dto) {
        StockMovementDTO data = stockMovementService.create(dto);
        return ResponseUtil.ok("Movimiento de producto encontrado exitosamente", data);
    }
}
