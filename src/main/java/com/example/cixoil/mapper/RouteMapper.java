package com.example.cixoil.mapper;

import com.example.cixoil.dto.route.RouteDTO;
import com.example.cixoil.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    RouteDTO toDTO(Route route);
}
