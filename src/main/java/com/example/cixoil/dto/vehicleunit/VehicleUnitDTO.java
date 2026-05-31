package com.example.cixoil.dto.vehicleunit;

import com.example.cixoil.dto.client.ClientDTO;
import com.example.cixoil.dto.vehiclemodel.VehicleModelDTO;
import com.example.cixoil.model.VehicleUseType;

public record VehicleUnitDTO(
        Long id,
        VehicleModelDTO vehicleModel,
        ClientDTO client, // TODO: Cambiar dto sin datos contacto
        VehicleUseType vehicleUseType,
        String plate,
        String color,
        Integer status
) {
}
