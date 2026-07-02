package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.productrecommendation.ProductRecommendationDTO;
import com.example.cixoil.dto.productrecommendation.RecommendationRequestDTO;
import com.example.cixoil.service.ProductRecommendationService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<?> create(@Valid @RequestBody RecommendationRequestDTO dto) {
        ProductRecommendationDTO created = productRecommendationService.create(dto);
        return ResponseUtil.ok("Recomendación creada correctamente", created);
    }
}
