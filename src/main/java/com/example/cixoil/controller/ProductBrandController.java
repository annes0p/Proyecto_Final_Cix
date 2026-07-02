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

import com.example.cixoil.dto.productbrand.ProductBrandDTO;
import com.example.cixoil.dto.productbrand.ProductBrandSaveDTO;
import com.example.cixoil.service.ProductBrandService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<?> create(@Valid @RequestBody ProductBrandSaveDTO dto) {
        ProductBrandDTO created = productBrandService.create(dto);
        return ResponseUtil.ok("Marca de producto creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductBrandSaveDTO dto, @PathVariable Long id) {
        ProductBrandDTO updated = productBrandService.update(dto, id);
        return ResponseUtil.ok("Marca de producto actualizada correctamente", updated);
    }
}
