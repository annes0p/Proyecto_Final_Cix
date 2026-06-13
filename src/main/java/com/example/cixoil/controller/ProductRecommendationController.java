package com.example.cixoil.controller;

import com.example.cixoil.dto.productrecommendation.ProductRecommendationDTO;
import com.example.cixoil.dto.productrecommendation.RecommendationRequestDTO;
import com.example.cixoil.service.ProductRecommendationService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class ProductRecommendationController {

    private final ProductRecommendationService productRecommendationService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<ProductRecommendationDTO> data = productRecommendationService.findAll();
        return ResponseUtil.ok("Recomendaciones obtenidas correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductRecommendationDTO existent = productRecommendationService.getById(id);
        return ResponseUtil.ok("Recomendación obtenida correctamente", existent);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RecommendationRequestDTO dto) {
        ProductRecommendationDTO created = productRecommendationService.create(dto);
        return ResponseUtil.ok("Recomendación creada correctamente", created);
    }
}
