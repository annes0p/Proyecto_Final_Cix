package com.example.cixoil.mapper;

import com.example.cixoil.dto.vehiclemodel.VehicleModelDTO;
import com.example.cixoil.model.VehicleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleModelMapper {

    VehicleModelDTO toDTO(VehicleModel vehicleModel);
}
