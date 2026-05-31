package com.example.cixoil.mapper;

import com.example.cixoil.dto.vehicleunit.VehicleUnitDTO;
import com.example.cixoil.model.VehicleUnit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleUnitMapper {
    VehicleUnitDTO toDTO(VehicleUnit vehicleUnit);
}
