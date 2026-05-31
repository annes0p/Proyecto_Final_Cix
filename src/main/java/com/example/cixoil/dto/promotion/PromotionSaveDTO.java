package com.example.cixoil.dto.promotion;

public record PromotionSaveDTO(
        String name,
        Long idTriggerProduct,
        Long triggerQuantity,
        Long idBonusProduct,
        Integer startMonth,
        Integer startDay,
        Integer endMonth,
        Integer endDay,
        Long idPromotionType,
        Boolean autoActivate
) {
}
