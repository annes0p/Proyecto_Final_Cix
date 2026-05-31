package com.example.cixoil.mapper;

import com.example.cixoil.dto.supplier.SupplierDTO;
import com.example.cixoil.model.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierDTO toDTO(Supplier supplier);
}
