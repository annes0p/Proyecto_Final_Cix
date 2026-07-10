package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.supplier.SupplierDTO;
import com.example.cixoil.dto.supplier.SupplierProductsSaveDTO;
import com.example.cixoil.dto.supplier.SupplierSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.SupplierService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<SupplierDTO> data = supplierService.findNotDeleted();
        return ResponseUtil.ok("Proveedores obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        SupplierDTO existent = supplierService.getById(id);
        return ResponseUtil.ok("Proveedor obtenido correctamente", existent);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> findProducts(@PathVariable Long id) {
        List<ProductDTO> data = supplierService.listSupplierProducts(id);
        return ResponseUtil.ok("Productos del proveedor obtenidos correctamente", data);
    }

    @PutMapping("/{id}/products")
    public ResponseEntity<?> updateProducts(
            @PathVariable Long id,
            @Valid @RequestBody SupplierProductsSaveDTO dto
    ) {
        List<ProductDTO> data = supplierService.updateProducts(id, dto.productIds());
        return ResponseUtil.ok("Productos del proveedor actualizados correctamente", data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SupplierSaveDTO dto) {
        SupplierDTO created = supplierService.create(dto);
        return ResponseUtil.ok("Proveedor creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody SupplierSaveDTO dto, @PathVariable Long id) {
        SupplierDTO updated = supplierService.update(dto, id);
        return ResponseUtil.ok("Proveedor actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        SupplierDTO toggled = supplierService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Proveedor activado correctamente" : "Proveedor desactivado correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseUtil.ok("Proveedor eliminado correctamente", null);
    }
}
