package com.example.cixoil.controller;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.supplier.SupplierDTO;
import com.example.cixoil.dto.supplier.SupplierSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.SupplierService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SupplierSaveDTO dto) {
        SupplierDTO created = supplierService.create(dto);
        return ResponseUtil.ok("Proveedor creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody SupplierSaveDTO dto, @PathVariable Long id) {
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
