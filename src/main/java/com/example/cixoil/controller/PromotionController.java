package com.example.cixoil.controller;

import com.example.cixoil.dto.promotion.PromotionDTO;
import com.example.cixoil.dto.promotion.PromotionSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.PromotionService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<PromotionDTO> data = promotionService.findNotDeleted();
        return ResponseUtil.ok("Promociones obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PromotionDTO existent = promotionService.getById(id);
        return ResponseUtil.ok("Promoción obtenida correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PromotionSaveDTO dto) {
        PromotionDTO created = promotionService.create(dto);
        return ResponseUtil.ok("Promoción creada correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody PromotionSaveDTO dto,
            @PathVariable Long id
    ) {
        PromotionDTO updated = promotionService.update(dto, id);
        return ResponseUtil.ok("Promoción actualizada correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {

        PromotionDTO toggled = promotionService.toggleStatus(id);

        String msg = toggled.status().equals(Status.ACTIVE.getValue())
                ? "Promoción activada correctamente"
                : "Promoción desactivada correctamente";

        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseUtil.ok("Promoción eliminada correctamente", null);
    }
}