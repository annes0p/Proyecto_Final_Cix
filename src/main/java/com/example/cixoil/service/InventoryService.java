package com.example.cixoil.service;

import com.example.cixoil.dto.inventory.InventoryDTO;
import com.example.cixoil.dto.inventory.InventorySaveDTO;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.InventoryMapper;
import com.example.cixoil.model.Inventory;
import com.example.cixoil.model.Product;
import com.example.cixoil.repository.InventoryRepository;
import com.example.cixoil.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<InventoryDTO> listAll() {
        return inventoryRepository.findAll()
                .stream().map(inventoryMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public InventoryDTO findById(Long id) {
        return inventoryMapper.toDTO(requireInventoryById(id,
                "Inventario no encontrado"));
    }

    @Transactional(readOnly = true)
    public Inventory findEntityByProductId(Long id) {
        Product product = requireProductById(id, "No se encontró producto");
        return inventoryRepository.findByProduct_Id(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
    }

    @Transactional(readOnly = true)
    public InventoryDTO findByProductId(Long id) {
        return inventoryMapper.toDTO(findEntityByProductId(id));
    }

    @Transactional
    public InventoryDTO create(InventorySaveDTO dto) {

        validateInventoryNotExistForProductId(dto.idProduct());

        Product product = requireProductById(dto.idProduct(), "No se encontró producto");

        Inventory created = Inventory.builder()
                .product(product)
                .stock(dto.stock())
                .minStock(dto.minStock())
                .build();

        return inventoryMapper.toDTO(inventoryRepository.save(created));
    }

    @Transactional
    public InventoryDTO update(InventorySaveDTO dto, Long id) {
        Inventory existent = requireInventoryById(id, "No se encontró inventario");

        if (!existent.getProduct().getId().equals(dto.idProduct())) {
            validateInventoryNotExistForProductId(dto.idProduct());

            Product product = requireProductById(
                    dto.idProduct(),
                    "No se encontró producto"
            );

            existent.setProduct(product);
        }

        existent.setStock(dto.stock());
        existent.setMinStock(dto.minStock());

        return inventoryMapper.toDTO(inventoryRepository.save(existent));
    }

    @Transactional
    public void updateStock(Long id, Long newStock) {
        Inventory inventory = findEntityByProductId(id);

        inventory.setStock(newStock);
    }

    // Require

    private Inventory requireInventoryById(Long id, String errorMessage) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Product requireProductById(Long id, String errorMessage) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    // Validations

    private void validateInventoryNotExistForProductId(Long id) {
        if (inventoryRepository.findByProduct_Id(id).isPresent())
            throw new BusinessException("No se puede crear inventario a un producto que ya lo tiene");
    }
}
