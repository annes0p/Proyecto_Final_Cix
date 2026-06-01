package com.example.cixoil.mapper;

import com.example.cixoil.dto.inventory.InventoryDTO;
import com.example.cixoil.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDTO toDTO(Inventory inventory);
}
