package com.example.cixoil.controller;

import com.example.cixoil.dto.category.CategoryDTO;
import com.example.cixoil.dto.category.CategorySaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.CategoryService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<CategoryDTO> data = categoryService.findNotDeleted();
        return ResponseUtil.ok("Categorías obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CategoryDTO existent = categoryService.getById(id);
        return ResponseUtil.ok("Categorías obtenidas correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategorySaveDTO dto) {
        CategoryDTO created = categoryService.create(dto);
        return ResponseUtil.ok("Categoría creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategorySaveDTO dto, @PathVariable Long id) {
        CategoryDTO updated = categoryService.update(dto, id);
        return ResponseUtil.ok("Categoría actualizada correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        CategoryDTO toggled = categoryService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Categoría activada correctamente" : "Categoría desactivada correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseUtil.ok("Categoría eliminada correctamente", null);
    }
}
