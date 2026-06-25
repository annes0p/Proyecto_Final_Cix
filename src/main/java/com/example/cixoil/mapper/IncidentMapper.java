package com.example.cixoil.mapper;

import com.example.cixoil.dto.incident.IncidentDTO;
import com.example.cixoil.model.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        IncidentCategoryMapper.class,
        IncidentTypeMapper.class
})
public interface IncidentMapper {

    IncidentDTO toDTO(Incident incident);
}
