package com.example.cixoil.service;

import com.example.cixoil.dto.stockmovement.StockMovementDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.enums.StockMovementType;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.StockMovementMapper;
import com.example.cixoil.model.Inventory;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.StockMovement;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.StockMovementRepository;
import com.example.cixoil.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                "Inventario no encontrado"));
    }

    @Transactional
    public StockMovementDTO create(StockMovementSaveDTO dto) {

        ValidationUtil.validateQuantity(dto.quantity());

        Product product = requireProductById(dto.idProduct(), "No se encontró producto");
        Inventory inventory = inventoryService.findEntityByProductId(product.getId());

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
