package com.example.cixoil.controller;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.product.ProductSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.ProductService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<ProductDTO> data = productService.findNotDeleted();
        return ResponseUtil.ok("Productos obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductDTO existent = productService.getById(id);
        return ResponseUtil.ok("Productos obtenidos correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute ProductSaveDTO dto) {
        ProductDTO created = productService.create(dto);
        return ResponseUtil.ok("Producto creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute ProductSaveDTO dto, @PathVariable Long id) {
        ProductDTO updated = productService.update(dto, id);
        return ResponseUtil.ok("Producto actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        ProductDTO toggled = productService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Producto activado correctamente" : "Producto desactivado correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseUtil.ok("Producto eliminado correctamente", null);
    }
}
