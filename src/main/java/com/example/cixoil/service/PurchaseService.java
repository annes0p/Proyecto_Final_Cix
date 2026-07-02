package com.example.cixoil.service;

import com.example.cixoil.dto.purchase.PartialReceiveDTO;
import com.example.cixoil.dto.purchase.PurchaseDTO;
import com.example.cixoil.dto.purchase.PurchaseSaveDTO;
import com.example.cixoil.dto.purchase.PartialReceiveItemDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.enums.ReceptionStatus;
import com.example.cixoil.exception.BusinessException;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    private final SupplierRepository supplierRepository;
    private final PurchaseDetailService purchaseDetailService;
    private final StockMovementService stockMovementService;

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

        List<PurchaseDetail> details = new ArrayList<>(dto.details()
                .stream().map(purchaseDetailService::build).toList());

        BigDecimal total = details.stream()
                .map(PurchaseDetail::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Purchase.PurchaseBuilder builder = Purchase.builder()
                .supplier(supplier);
        if (dto.purchasedAt() != null) builder.purchasedAt(dto.purchasedAt());
        builder.estimatedDeliveryAt(dto.estimatedDeliveryAt())
                .deliveredAt(dto.deliveredAt())
                .total(total);

        Purchase created = builder.build();
        details.forEach(created::addDetail);

        purchaseRepository.save(created);

        return purchaseMapper.toDTO(created);
    }

    @Transactional
    public PurchaseDTO receive(Long id) {
        Purchase purchase = requirePurchaseById(id, "No se encontro la compra");

        if (purchase.getReceptionStatus() == ReceptionStatus.RECEIVED)
            throw new BusinessException("Esta orden ya fue recibida por completo");

        List<StockMovementSaveDTO> movements = stockMovementService.generatePurchaseMovements(
                purchase.getDetails(), LocalDateTime.now());

        purchase.getDetails().forEach(d -> d.setReceivedQuantity(d.getQuantity()));
        purchase.setReceptionStatus(ReceptionStatus.RECEIVED);

        Purchase saved = purchaseRepository.save(purchase);
        stockMovementService.saveAll(movements);

        return purchaseMapper.toDTO(saved);
    }

    @Transactional
    public PurchaseDTO partiallyReceive(Long id, PartialReceiveDTO dto) {
        Purchase purchase = requirePurchaseById(id, "No se encontro la compra");

        if (purchase.getReceptionStatus() == ReceptionStatus.RECEIVED)
            throw new BusinessException("Esta orden ya fue recibida por completo");

        if (dto.items() == null || dto.items().isEmpty())
            throw new BusinessException("Debes indicar al menos un producto recibido");

        for (PartialReceiveItemDTO item : dto.items()) {
            if (item.idProduct() == null)
                throw new BusinessException("Falta el producto en uno de los items recibidos");

            if (item.quantity() == null || item.quantity() <= 0)
                throw new BusinessException("La cantidad recibida debe ser mayor a 0");

            PurchaseDetail detalle = requireDetailByProduct(purchase, item.idProduct());

            long yaRecibido = detalle.getReceivedQuantity() == null ? 0 : detalle.getReceivedQuantity();
            long pendiente = detalle.getQuantity() - yaRecibido;

            if (item.quantity() > pendiente)
                throw new BusinessException(
                        "Solo faltan " + pendiente + " unidades de " + detalle.getProduct().getName()
                                + ", no puedes recibir " + item.quantity()
                );

            detalle.setReceivedQuantity(yaRecibido + item.quantity());
        }

        boolean recibidoCompleto = purchase.getDetails().stream()
                .allMatch(d -> (d.getReceivedQuantity() == null ? 0 : d.getReceivedQuantity()) >= d.getQuantity());

        purchase.setReceptionStatus(recibidoCompleto ? ReceptionStatus.RECEIVED : ReceptionStatus.PARTIALLY_RECEIVED);

        List<StockMovementSaveDTO> movements = stockMovementService.generatePartialPurchaseMovements(
                dto.items(), LocalDateTime.now());

        Purchase saved = purchaseRepository.save(purchase);
        stockMovementService.saveAll(movements);

        return purchaseMapper.toDTO(saved);
    }

    private Purchase requirePurchaseById(Long id, String errorMessage) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Supplier requireSupplierById(Long id, String errorMessage) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private PurchaseDetail requireDetailByProduct(Purchase purchase, Long idProduct) {
        return purchase.getDetails().stream()
                .filter(d -> d.getProduct().getId().equals(idProduct))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Ese producto no pertenece a esta orden de compra"));
    }
}