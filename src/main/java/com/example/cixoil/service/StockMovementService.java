package com.example.cixoil.service;

import com.example.cixoil.dto.stockmovement.StockMovementDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.enums.StockMovementType;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.StockMovementMapper;
import com.example.cixoil.model.*;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.StockMovementRepository;
import com.example.cixoil.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StockMovementService {
    
    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;

    @Transactional(readOnly = true)
    public List<StockMovementDTO> listAll() {
        return stockMovementRepository.findAll()
                .stream().map(stockMovementMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public StockMovementDTO findById(Long id) {
        return stockMovementMapper.toDTO(requireMovementById(id,
                "Movimiento no encontrado"));
    }

    @Transactional
    public StockMovementDTO create(StockMovementSaveDTO dto) {

        ValidationUtil.validateQuantity(dto.quantity());

        Product product = requireProductById(dto.idProduct(), "No se encontró producto");
        Inventory inventory = inventoryService.findOrCreateInventoryByProductId(product.getId());

        Long initialStock = inventory.getStock();

        Long finalStock = initialStock;

        if (dto.stockMovementType().isAddition()) {
            finalStock += dto.quantity();
        } else {
            if (dto.quantity() > initialStock) throw new BusinessException("No hay suficiente stock");
            finalStock -= dto.quantity();
        }

        StockMovement.StockMovementBuilder builder = StockMovement.builder()
                .product(product)
                .initialStock(initialStock)
                .quantity(dto.quantity())
                .finalStock(finalStock)
                .stockMovementType(dto.stockMovementType());

        if (dto.movementDate() != null) builder.movementDate(dto.movementDate()); // TODO: Cambiar en otros defautls

        StockMovement created = builder.build();

        inventoryService.updateStock(inventory, finalStock);

        return stockMovementMapper.toDTO(stockMovementRepository.save(created));
    }

    public List<StockMovementSaveDTO> generatePurchaseMovements(List<PurchaseDetail> details, LocalDateTime date) {
        return details
                .stream()
                .map(d -> new StockMovementSaveDTO(
                        d.getProduct().getId(),
                        d.getQuantity(),
                        StockMovementType.IN,
                        date)
                )
                .toList();
    }

    public List<StockMovementSaveDTO> generateSaleMovements(List<SaleDetail> details, LocalDateTime date) {
        return details
                .stream()
                .map(d -> new StockMovementSaveDTO(
                        d.getProduct().getId(),
                        d.getQuantity(),
                        StockMovementType.OUT,
                        date)
                )
                .toList();
    }

    @Transactional
    public void saveAll(List<StockMovementSaveDTO> movements) {
        movements.forEach(this::create);
    }

    // Require

    private StockMovement requireMovementById(Long id, String errorMessage) {
        return stockMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Product requireProductById(Long id, String errorMessage) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
