package com.example.cixoil.mapper;

import com.example.cixoil.dto.productrecommendation.ProductRecommendationDTO;
import com.example.cixoil.model.ProductRecommendation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRecommendationMapper {
    ProductRecommendationDTO toDTO(ProductRecommendation productRecommendation);
}
