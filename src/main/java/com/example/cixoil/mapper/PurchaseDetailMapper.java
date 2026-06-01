package com.example.cixoil.mapper;

import com.example.cixoil.dto.purchasedetail.PurchaseDetailDTO;
import com.example.cixoil.model.PurchaseDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseDetailMapper {
    PurchaseDetailDTO toDTO(PurchaseDetail purchaseDetail);
}
