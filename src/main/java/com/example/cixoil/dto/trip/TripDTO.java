package com.example.cixoil.dto.trip;

import com.example.cixoil.dto.location.LocationDTO;
import com.example.cixoil.dto.sale.SaleRefDTO;
import com.example.cixoil.enums.ProgressStatus;

import java.time.LocalTime;

public record TripDTO(
        Long id,
        LocationDTO origin,
        LocationDTO destination,
        SaleRefDTO sale,
        LocalTime startTime,
        LocalTime endTime,
        ProgressStatus progressStatus,
        Integer status
) {
}
