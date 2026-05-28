package com.example.cixoil.service;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.product.ProductSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ProductMapper;
import com.example.cixoil.model.Category;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.ProductBrand;
import com.example.cixoil.repository.CategoryRepository;
import com.example.cixoil.repository.ProductBrandRepository;
import com.example.cixoil.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductBrandRepository productBrandRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findNotDeleted() {
        return productRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(productMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO getById(Long id) {
        return productRepository.findById(id).map(productMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrada"));
    }

    @Transactional
    public ProductDTO create(ProductSaveDTO dto) {
        ProductBrand brand = requireBrandById(dto.idBrand());
        Category category = requireCategoryById(dto.idCategory());

        Product created = Product.builder()
                .name(dto.name())
                .viscosity(dto.viscosity())
                .description(dto.description())
                .brand(brand)
                .category(category)
                .price(dto.price())
                .build();

        return productMapper.toDTO(productRepository.save(created));
    }

    @Transactional
    public ProductDTO update(ProductSaveDTO dto, Long id) {
        Product existent = requireProductById(id);

        ProductBrand brand = requireBrandById(dto.idBrand());
        Category category = requireCategoryById(dto.idCategory());

        existent.setName(dto.name());
        existent.setDescription(dto.description());
        existent.setBrand(brand);
        existent.setCategory(category);
        existent.setViscosity(dto.viscosity());
        existent.setPrice(dto.price());

        return productMapper.toDTO(productRepository.save(existent));
    }

    @Transactional
    public ProductDTO toggleStatus(Long id) {
        Product existent = requireProductById(id);

        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return productMapper.toDTO(productRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Product existent = requireProductById(id);
        existent.setStatus(Status.DELETED.getValue());
        productRepository.save(existent);
    }

    // Require

    private Product requireProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    private Category requireCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    }

    private ProductBrand requireBrandById(Long id) {
        return productBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca de producto no encontrada"));
    }
}
