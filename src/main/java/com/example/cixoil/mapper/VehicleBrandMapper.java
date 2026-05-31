package com.example.cixoil.mapper;

import com.example.cixoil.dto.vehiclebrand.VehicleBrandDTO;
import com.example.cixoil.model.VehicleBrand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleBrandMapper {

    VehicleBrandDTO toDTO(VehicleBrand vehicleBrand);
}
