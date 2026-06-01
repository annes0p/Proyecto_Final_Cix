package com.example.cixoil.mapper;

import com.example.cixoil.dto.stockmovement.StockMovementDTO;
import com.example.cixoil.model.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
    StockMovementDTO toDTO(StockMovement stockMovement);
}
