package com.example.cixoil.service;

import com.example.cixoil.dto.auth.AuthUserDTO;
import com.example.cixoil.dto.user.UserDTO;
import com.example.cixoil.dto.user.UserSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.UserMapper;
import com.example.cixoil.model.Role;
import com.example.cixoil.model.User;
import com.example.cixoil.repository.RoleRepository;
import com.example.cixoil.repository.UserRepository;
import com.example.cixoil.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserDTO> findNotDeleted() {
        return userRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(userMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario"));
    }

    @Transactional(readOnly = true)
    public Optional<AuthUserDTO> getAuthDTOById(Long id) {
        return userRepository.findById(id).map(userMapper::toAuthUserDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).map(userMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).map(userMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getByUsernameOrEmail(String input) {
        return userRepository.findByEmailIgnoreCase(input)
                .or(() -> userRepository.findByUsernameIgnoreCase(input))
                .map(userMapper::toDTO);
    }

    @Transactional
    public UserDTO create(UserSaveDTO dto) {
        Role role = requireRoleById(dto.roleId());

        User created = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(role)
                .build();

        return userMapper.toDTO(userRepository.save(created));
    }

    @Transactional
    public UserDTO update(Long id, UserSaveDTO dto) {
        Role role = requireRoleById(dto.roleId());

        User existent = requireUserById(id);

        existent.setUsername(dto.username());
        existent.setEmail(dto.email());
        existent.setRole(role);
        if (!isPasswordBlank(dto.password()))
            existent.setPassword(passwordEncoder.encode(dto.password()));

        return userMapper.toDTO(userRepository.save(existent));
    }

    @Transactional
    public UserDTO toggleStatus(Long id) {
        User existent = requireUserById(id);

        existent.setStatus(
                Objects.equals(existent.getStatus(), Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return userMapper.toDTO(userRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        User existent = requireUserById(id);
        existent.setStatus(Status.DELETED.getValue());
        userRepository.save(existent);
    }

    // Require

    private User requireUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    private Role requireRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
    }

    @Transactional(readOnly = true)
    public User requireByUsernameOrEmail(String input) {
        return userRepository.findByEmailIgnoreCase(input)
                .or(() -> userRepository.findByUsernameIgnoreCase(input))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado con ese nombre o correo"));
    }

    //Validations

    public boolean validatePassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }

    private boolean isPasswordBlank(String password) {
        return password == null || password.trim().isEmpty();
    }
}
