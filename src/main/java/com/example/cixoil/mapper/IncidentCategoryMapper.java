package com.example.cixoil.mapper;

import com.example.cixoil.dto.incidentcategory.IncidentCategoryRefDTO;
import com.example.cixoil.model.IncidentCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidentCategoryMapper {

    IncidentCategoryRefDTO toRefDTO(IncidentCategory incidentCategory);
}
