package com.example.cixoil.mapper;

import com.example.cixoil.dto.incidenttype.IncidentTypeRefDTO;
import com.example.cixoil.model.IncidentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidentTypeMapper {

    IncidentTypeRefDTO toRefDTO(IncidentType incidentType);
}
