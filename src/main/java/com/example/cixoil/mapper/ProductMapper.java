package com.example.cixoil.mapper;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.model.Category;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.ProductBrand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Cambiar a no flatten
    @Mapping(target = "brand", expression = "java(map(product.getBrand()))")
    @Mapping(target = "category", expression = "java(map(product.getCategory()))")
    ProductDTO toDTO(Product product);

    default String map(ProductBrand productBrand) {
        return productBrand != null ? productBrand.getName() : null;
    }

    default String map(Category category) {
        return category != null ? category.getName() : null;
    }
}
