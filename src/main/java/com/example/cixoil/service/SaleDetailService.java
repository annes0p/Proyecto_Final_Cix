package com.example.cixoil.service;

import com.example.cixoil.dto.saledetail.SaleDetailSaveDTO;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.SaleDetail;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.SaleDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    @Transactional
    public SaleDetail build(SaleDetailSaveDTO dto) {
        Product product = requireProductById(dto.idProduct(), "No se encontró producto");

        BigDecimal price = product.getPrice();
        final BigDecimal igv = BigDecimal.valueOf(0.18);

        BigDecimal quantity = BigDecimal.valueOf(dto.quantity());
        BigDecimal total = price.multiply(quantity);

        BigDecimal subtotal = total.divide(
                BigDecimal.ONE.add(igv),
                2, RoundingMode.HALF_UP);

        BigDecimal taxAmount = total.subtract(subtotal);

        return SaleDetail.builder()
                .product(product)
                .quantity(dto.quantity())
                .unitPrice(price)
                .subtotal(subtotal)
                .taxAmount(taxAmount)
                .total(total)
                .build();
    }

    // Require

    private Product requireProductById(Long id, String errorMessage) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

}
