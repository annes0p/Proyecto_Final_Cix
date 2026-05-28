package com.example.cixoil.mapper;

import com.example.cixoil.dto.location.LocationDTO;
import com.example.cixoil.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDTO(Location location);
}
