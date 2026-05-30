package com.example.cixoil.mapper;

import com.example.cixoil.dto.module.PermissionDTO;
import com.example.cixoil.dto.role.RoleDTO;
import com.example.cixoil.dto.role.RoleRefDTO;
import com.example.cixoil.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ModuleMapper.class)
public interface RoleMapper {

    RoleDTO toDTO(Role role);

    RoleRefDTO toRefDTO(Role role);

    @Mapping(source = "name", target = "role")
    PermissionDTO toPermissionDTO(Role role);

}
