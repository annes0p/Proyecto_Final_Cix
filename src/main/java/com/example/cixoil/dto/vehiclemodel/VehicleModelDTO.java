package com.example.cixoil.dto.vehiclemodel;

import com.example.cixoil.dto.vehiclebrand.VehicleBrandDTO;
import com.example.cixoil.dto.vehicletype.VehicleTypeRefDTO;
import com.example.cixoil.enums.FuelType;
import com.example.cixoil.enums.TransmissionType;

public record VehicleModelDTO(
        Long id,
        String model,
        Integer year,
        VehicleBrandDTO vehicleBrand,
        VehicleTypeRefDTO vehicleType,
        Integer horsePower,
        Integer motorCC,
        FuelType fuelType,
        TransmissionType transmissionType,
        Integer status
) {
}
