package com.example.cixoil.mapper;

import com.example.cixoil.dto.purchase.PurchaseDTO;
import com.example.cixoil.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseDTO toDTO(Purchase purchase);
}
