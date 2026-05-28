package com.example.cixoil.dto.route;

import com.example.cixoil.dto.trip.TripDTO;
import com.example.cixoil.dto.user.UserRefDTO;
import com.example.cixoil.enums.ProgressStatus;

import java.time.LocalDate;
import java.util.List;

public record RouteDTO(
        Long id,
        UserRefDTO user,
        LocalDate routeDate,
        ProgressStatus progressStatus,
        List<TripDTO> trips,
        Integer status
) {
}
