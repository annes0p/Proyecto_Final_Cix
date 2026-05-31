package com.example.cixoil.mapper;

import com.example.cixoil.dto.vehicleusetype.VehicleUseTypeDTO;
import com.example.cixoil.dto.vehicleusetype.VehicleUseTypeRefDTO;
import com.example.cixoil.model.VehicleUseType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleUseTypeMapper {
    VehicleUseTypeDTO toDTO(VehicleUseType vehicleUseType);

    VehicleUseTypeRefDTO toRefDTO(VehicleUseType vehicleUseType);
}
