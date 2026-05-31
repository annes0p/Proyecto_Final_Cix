package com.example.cixoil.dto.promotion;

public record PromotionDTO(
        Long id,
        String name,
        String triggerProduct,
        Long triggerQuantity,
        String bonusProduct,
        Integer startMonth,
        Integer startDay,
        Integer endMonth,
        Integer endDay,
        String promotionType,
        Integer status,
        Boolean autoActivate
) {
}
