package com.example.cixoil.dto.vehiclemodel;

import com.example.cixoil.enums.FuelType;
import com.example.cixoil.enums.TransmissionType;

public record VehicleModelSaveDTO(
        String model,
        Integer year,
        Long idVehicleBrand,
        Long idVehicleType,
        Integer horsePower,
        Integer motorCC,
        FuelType fuelType,
        TransmissionType transmissionType
) {
}
