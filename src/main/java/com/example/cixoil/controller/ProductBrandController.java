package com.example.cixoil.controller;

import com.example.cixoil.dto.product.ProductBrandDTO;
import com.example.cixoil.dto.product.ProductBrandSaveDTO;
import com.example.cixoil.service.ProductBrandService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-brands")
public class ProductBrandController {
    
    private final ProductBrandService productBrandService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<ProductBrandDTO> data = productBrandService.findAll();
        return ResponseUtil.ok("Marcas de producto obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductBrandDTO existent = productBrandService.getById(id);
        return ResponseUtil.ok("Marcas de producto obtenidas correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductBrandSaveDTO dto) {
        ProductBrandDTO created = productBrandService.create(dto);
        return ResponseUtil.ok("Marca de producto creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductBrandSaveDTO dto, @PathVariable Long id) {
        ProductBrandDTO updated = productBrandService.update(dto, id);
        return ResponseUtil.ok("Marca de producto actualizada correctamente", updated);
    }
}
