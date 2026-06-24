package com.example.cixoil.mapper;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.model.IncidentCategory;
import org.springframework.stereotype.Component;

@Component
public class SelectMapper {

    public SelectDTO<Long> fromIncidentCategory(IncidentCategory entity) {
        return new SelectDTO<>(entity.getId(), entity.getName());
    }
}
