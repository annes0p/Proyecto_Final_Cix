package com.example.cixoil.mapper;

import com.example.cixoil.dto.module.ModuleDTO;
import com.example.cixoil.model.Module;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    ModuleDTO toDto(Module module);

}
