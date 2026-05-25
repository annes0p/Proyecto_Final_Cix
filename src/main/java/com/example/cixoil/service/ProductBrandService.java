package com.example.cixoil.service;

import com.example.cixoil.dto.product.ProductBrandDTO;
import com.example.cixoil.dto.product.ProductBrandSaveDTO;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ProductBrandMapper;
import com.example.cixoil.model.ProductBrand;
import com.example.cixoil.repository.ProductBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBrandService {

    private final ProductBrandRepository productBrandRepository;
    private final ProductBrandMapper productBrandMapper;

    @Transactional(readOnly = true)
    public List<ProductBrandDTO> findAll() {
        return productBrandRepository.findAll()
                .stream().map(productBrandMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProductBrandDTO getById(Long id) {
        return productBrandRepository.findById(id).map(productBrandMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Marca de producto no encontrada"));
    }

    @Transactional
    public ProductBrandDTO create(ProductBrandSaveDTO dto) {
        ProductBrand created = ProductBrand.builder()
                .name(dto.name())
                .build();

        return productBrandMapper.toDTO(productBrandRepository.save(created));
    }

    @Transactional
    public ProductBrandDTO update(ProductBrandSaveDTO dto, Long id) {
        ProductBrand existent = requireBrandById(id);

        existent.setName(dto.name());

        return productBrandMapper.toDTO(productBrandRepository.save(existent));
    }

    // Require

    private ProductBrand requireBrandById(Long id) {
        return productBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca de producto no encontrada"));
    }
}
