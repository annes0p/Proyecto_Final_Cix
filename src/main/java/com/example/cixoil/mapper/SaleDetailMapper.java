package com.example.cixoil.mapper;

import com.example.cixoil.dto.saledetail.SaleDetailDTO;
import com.example.cixoil.model.SaleDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleDetailMapper {
    SaleDetailDTO toDTO(SaleDetail saleDetail);
}
