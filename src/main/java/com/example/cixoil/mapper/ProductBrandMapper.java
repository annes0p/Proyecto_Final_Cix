package com.example.cixoil.mapper;

import com.example.cixoil.dto.productbrand.ProductBrandDTO;
import com.example.cixoil.model.ProductBrand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductBrandMapper {

    ProductBrandDTO toDTO(ProductBrand productBrand);
}
