package com.example.cixoil.mapper;

import com.example.cixoil.dto.AuthUserDTO;
import com.example.cixoil.dto.UserDTO;
import com.example.cixoil.model.Role;
import com.example.cixoil.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    AuthUserDTO toAuthUserDTO(User user);

    default String map(Role role) {
        return role != null ? role.getName() : null;
    }
}
