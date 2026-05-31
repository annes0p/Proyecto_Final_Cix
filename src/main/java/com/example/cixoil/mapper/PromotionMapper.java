package com.example.cixoil.mapper;

import com.example.cixoil.dto.promotion.PromotionDTO;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.Promotion;
import com.example.cixoil.model.PromotionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mapping(target = "triggerProduct",
            expression = "java(map(promotion.getTriggerProduct()))")
    @Mapping(target = "bonusProduct",
            expression = "java(map(promotion.getBonusProduct()))")
    @Mapping(target = "promotionType",
            expression = "java(map(promotion.getPromotionType()))")
    PromotionDTO toDTO(Promotion promotion);

    default String map(Product product) {
        return product != null ? product.getName() : null;
    }

    default String map(PromotionType promotionType) {
        return promotionType != null ? promotionType.getName() : null;
    }
}
