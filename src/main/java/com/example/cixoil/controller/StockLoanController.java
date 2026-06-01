package com.example.cixoil.controller;

import com.example.cixoil.dto.stockloan.StockLoanDTO;
import com.example.cixoil.dto.stockloan.StockLoanSaveDTO;
import com.example.cixoil.service.StockLoanService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loans")
public class StockLoanController {
    
    private final StockLoanService stockLoanService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<StockLoanDTO> data = stockLoanService.findAll();
        return ResponseUtil.ok("Préstamos obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        StockLoanDTO existent = stockLoanService.getById(id);
        return ResponseUtil.ok("Préstamo obtenido correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StockLoanSaveDTO dto) {
        StockLoanDTO created = stockLoanService.create(dto);
        return ResponseUtil.ok("Préstamo creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody StockLoanSaveDTO dto, @PathVariable Long id) {
        StockLoanDTO updated = stockLoanService.update(dto, id);
        return ResponseUtil.ok("Préstamo actualizado correctamente", updated);
    }
}
