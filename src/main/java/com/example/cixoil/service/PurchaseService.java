package com.example.cixoil.service;

import com.example.cixoil.dto.purchase.PurchaseDTO;
import com.example.cixoil.dto.purchase.PurchaseSaveDTO;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.PurchaseMapper;
import com.example.cixoil.model.Purchase;
import com.example.cixoil.model.PurchaseDetail;
import com.example.cixoil.model.Supplier;
import com.example.cixoil.repository.PurchaseRepository;
import com.example.cixoil.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final SupplierRepository supplierRepository;
    private final PurchaseDetailService purchaseDetailService;

    @Transactional(readOnly = true)
    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll()
                .stream().map(purchaseMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public PurchaseDTO getById(Long id) {
        Purchase purchase = requirePurchaseById(id, "Compra no encontrada");
        return purchaseMapper.toDTO(purchase);
    }

    @Transactional
    public PurchaseDTO create(PurchaseSaveDTO dto) {
        Supplier supplier = requireSupplierById(dto.idSupplier(),
                "Proveedor no encontrado");

        List<PurchaseDetail> details = dto.details()
                .stream().map(purchaseDetailService::create).toList();

        BigDecimal total = details.stream()
                .map(PurchaseDetail::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Purchase created = Purchase.builder()
                .supplier(supplier)
                .purchasedAt(dto.purchasedAt())
                .estimatedDeliveryAt(dto.estimatedDeliveryAt())
                .deliveredAt(dto.deliveredAt())
                .total(total)
                .receptionStatus(dto.receptionStatus())
                .details(details)
                .build();

        return purchaseMapper.toDTO(purchaseRepository.save(created));
    }

    // Require

    private Purchase requirePurchaseById(Long id, String errorMessage) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Supplier requireSupplierById(Long id, String errorMessage) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
