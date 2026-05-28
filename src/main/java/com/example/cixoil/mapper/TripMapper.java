package com.example.cixoil.mapper;

import com.example.cixoil.dto.trip.TripDTO;
import com.example.cixoil.model.Trip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripMapper {

    TripDTO toDTO(Trip trip);
}
