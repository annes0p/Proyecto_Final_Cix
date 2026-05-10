package com.example.cixoil.mapper;

import com.example.cixoil.dto.PermissionDTO;
import com.example.cixoil.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ModuleMapper.class)
public interface RoleMapper {

    @Mapping(source = "name", target = "role")
    PermissionDTO toPermissionDTO(Role role);

}
