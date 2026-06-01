package com.example.cixoil.mapper;

import com.example.cixoil.dto.sale.SaleDTO;
import com.example.cixoil.model.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleDTO toDTO(Sale sale);
}
