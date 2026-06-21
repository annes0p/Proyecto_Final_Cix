package com.example.cixoil.service;

import com.example.cixoil.dto.purchasedetail.PurchaseDetailSaveDTO;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.PurchaseDetail;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.PurchaseDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PurchaseDetailService {

    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ProductRepository productRepository;

    @Transactional
    public PurchaseDetail create(PurchaseDetailSaveDTO dto) {
        Product product = requireProductById(dto.idProduct(), "No se encontró producto");

        BigDecimal lineTotal = BigDecimal.valueOf(dto.quantity()).multiply(product.getPrice());

        PurchaseDetail created = PurchaseDetail.builder()
                .product(product)
                .quantity(dto.quantity())
                .unitPrice(product.getPrice())
                .lineTotal(lineTotal)
                .build();

        return purchaseDetailRepository.save(created);
    }

    public PurchaseDetail build(PurchaseDetailSaveDTO dto) {
        Product product = requireProductById(dto.idProduct(), "No se encontró producto");

        BigDecimal lineTotal = BigDecimal.valueOf(dto.quantity()).multiply(product.getPrice());

        return PurchaseDetail.builder()
                .product(product)
                .quantity(dto.quantity())
                .unitPrice(product.getPrice())
                .lineTotal(lineTotal)
                .build();
    }

    // Require

    private Product requireProductById(Long id, String errorMessage) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
