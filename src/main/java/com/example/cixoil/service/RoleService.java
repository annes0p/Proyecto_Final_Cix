package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.dto.module.ModuleDTO;
import com.example.cixoil.dto.role.RoleDTO;
import com.example.cixoil.dto.role.RoleRefDTO;
import com.example.cixoil.dto.role.RoleSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ModuleMapper;
import com.example.cixoil.mapper.RoleMapper;
import com.example.cixoil.model.Module;
import com.example.cixoil.model.Role;
import com.example.cixoil.repository.ModuleRepository;
import com.example.cixoil.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    @Transactional(readOnly = true)
    public List<RoleDTO> findNotDeleted() {
        return roleRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(roleMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return roleRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public RoleDTO getById(Long id) {
        Role role = requireRoleById(id, "Rol no encontrado");
        return roleMapper.toDTO(role);
    }

    @Transactional(readOnly = true)
    public List<ModuleDTO> findModulesByRouteId(Long id) {
        Role role = requireRoleById(id, "Rol no encontrado");
        return role.getModules().stream().map(moduleMapper::toDTO).toList();
    }

    @Transactional
    public RoleDTO create(RoleSaveDTO dto) {

        List<Module> modules = requireAllModulesById(
                dto.idModules(),
                "No se encontró el módulo al crear rol"
        );

        Role created = Role.builder()
                .name(dto.name())
                .description(dto.description())
                .modules(modules)
                .build();

        return roleMapper.toDTO(roleRepository.save(created));
    }

    @Transactional
    public RoleDTO update(RoleSaveDTO dto, Long id) {
        Role existent = requireRoleById(id, "Rol no encontrado para actualizar");

        List<Module> modules = requireAllModulesById(
                dto.idModules(),
                "No se encontró el módulo al actualizar rol"
        );

        existent.setName(dto.name());
        existent.setDescription(dto.description());
        existent.getModules().clear();
        existent.getModules().addAll(modules);

        return roleMapper.toDTO(roleRepository.save(existent));
    }

    @Transactional
    public RoleDTO toggleStatus(Long id) {
        Role existent = requireRoleById(id, "No se encontró rol para cambiar estado");
        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return roleMapper.toDTO(roleRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Role existent = requireRoleById(id, "No se encontró rol para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        roleRepository.save(existent);
    }

    // Require

    private Role requireRoleById(Long id, String errorMessage) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Module requireModuleById(Long id, String errorMessage) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private List<Module> requireAllModulesById(List<Long> idModules, String errorMessage) {
        return idModules
                .stream()
                .map(idModule -> requireModuleById(idModule, errorMessage))
                .toList();
    }
    
}
