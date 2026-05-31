package com.example.cixoil.mapper;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.model.Category;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.ProductBrand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
}
