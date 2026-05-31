package com.example.cixoil.mapper;

import com.example.cixoil.dto.vehicletype.VehicleTypeDTO;
import com.example.cixoil.dto.vehicletype.VehicleTypeRefDTO;
import com.example.cixoil.model.VehicleType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleTypeMapper {

    VehicleTypeDTO toDTO(VehicleType vehicleType);

    VehicleTypeRefDTO toRefDTO(VehicleType vehicleType);

}
