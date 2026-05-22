package com.example.cixoil.mapper;

import com.example.cixoil.dto.auth.AuthUserDTO;
import com.example.cixoil.dto.user.UserDTO;
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
