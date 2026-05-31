package com.example.cixoil.dto.vehicleunit;

import com.example.cixoil.dto.client.ClientRefDTO;
import com.example.cixoil.dto.vehiclemodel.VehicleModelDTO;
import com.example.cixoil.dto.vehicleusetype.VehicleUseTypeRefDTO;

public record VehicleUnitDTO(
        Long id,
        VehicleModelDTO vehicleModel,
        ClientRefDTO client,
        VehicleUseTypeRefDTO vehicleUseType,
        String plate,
        String color,
        Integer status
) {
}
