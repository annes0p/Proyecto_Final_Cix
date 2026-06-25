package com.example.cixoil.service;

import com.example.cixoil.dto.incident.IncidentDTO;
import com.example.cixoil.dto.incident.IncidentSaveDTO;
import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.IncidentMapper;
import com.example.cixoil.model.Incident;
import com.example.cixoil.model.IncidentCategory;
import com.example.cixoil.model.IncidentType;
import com.example.cixoil.repository.IncidentRepository;
import com.example.cixoil.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    private final IncidentTypeService incidentTypeService;
    private final IncidentCategoryService incidentCategoryService;

    @Transactional(readOnly = true)
    public List<IncidentDTO> listAll() {
        return incidentRepository.findAll().stream()
                .map(incidentMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<IncidentDTO> listOpen() {
        return incidentRepository.findByIncidentStatus(IncidentStatus.OPEN).stream()
                .map(incidentMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<IncidentDTO> listNotCanceled() {
        return incidentRepository.findByIncidentStatusNot(IncidentStatus.CANCELED).stream()
                .map(incidentMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public IncidentDTO getById(Long id) {
        Incident incident = requireIncidentById(id, "No se encontró incidente con esa id");
        return incidentMapper.toDTO(incident);
    }

    @Transactional
    public IncidentDTO create(IncidentSaveDTO dto) {
        IncidentType incidentType = incidentTypeService.requireIncidentTypeById(
                dto.idIncidentType(),
                "No se encontró el tipo de incidente al crear incidente"
        );

        IncidentCategory incidentCategory = incidentCategoryService.requireIncidentCategoryById(
                dto.idIncidentCategory(),
                "No se encontró la categoría de incidente al crear incidente"
        );

        Incident.IncidentBuilder builder = Incident.builder()
                .title(dto.title())
                .incidentType(incidentType)
                .description(dto.description())
                .incidentCategory(incidentCategory)
                .reference(dto.reference())
                .fullTitle(generateFullTitle(dto.title(), dto.reference()));

        if (dto.priority() != null) builder.priority(dto.priority());
        if (dto.reportedBy() != null) builder.reportedBy(dto.reportedBy());

        return incidentMapper.toDTO(incidentRepository.save(builder.build()));
    }

    @Transactional
    public IncidentDTO update(IncidentSaveDTO dto, Long id) {
        Incident existent = requireIncidentById(id, "No se encontró incidente para actualizar");

        IncidentType incidentType = incidentTypeService.requireIncidentTypeById(
                dto.idIncidentType(),
                "No se encontró el tipo de incidente al actualizar incidente"
        );
        IncidentCategory incidentCategory = incidentCategoryService.requireIncidentCategoryById(
                dto.idIncidentCategory(),
                "No se encontró la categoría de incidente al actualizar incidente"
        );

        existent.setTitle(dto.title());
        existent.setIncidentType(incidentType);
        existent.setPriority(dto.priority());
        existent.setDescription(dto.description());
        existent.setReportedBy(dto.reportedBy());
        existent.setIncidentCategory(incidentCategory);
        existent.setReference(dto.reference());
        existent.setFullTitle(generateFullTitle(existent.getTitle(), existent.getReference()));

        return incidentMapper.toDTO(incidentRepository.save(existent));
    }

    @Transactional
    public IncidentDTO resolve(Long id) {
        Incident incident = requireIncidentById(id, "No se encontró incidente para empezar");

        if (incident.getIncidentStatus() != IncidentStatus.OPEN)
            throw new BusinessException("Este incidente no está abierto");

        incident.setIncidentStatus(IncidentStatus.RESOLVED);
        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Transactional
    public IncidentDTO close(Long id) {
        Incident incident = requireIncidentById(id, "No se encontró incidente para cerrar");

        if (incident.getIncidentStatus() == IncidentStatus.CLOSED)
            throw new BusinessException("Este incidente ya está cerrado");

        incident.setIncidentStatus(IncidentStatus.CLOSED);
        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Transactional
    public IncidentDTO reopen(Long id) {
        Incident incident = requireIncidentById(id, "No se encontró incidente para reabrir");

        if (incident.getIncidentStatus() == IncidentStatus.OPEN)
            throw new BusinessException("Este incidente ya está abierto");

        incident.setIncidentStatus(IncidentStatus.OPEN);
        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Transactional
    public IncidentDTO cancel(Long id) {
        Incident incident = requireIncidentById(id, "No se encontró incidente para cancelar");

        if (incident.getIncidentStatus() == IncidentStatus.CANCELED)
            throw new BusinessException("Este incidente ya está cancelado");

        incident.setIncidentStatus(IncidentStatus.CANCELED);
        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    // Require

    public Incident requireIncidentById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el incidente"));
    }

    public Incident requireIncidentById(Long id, String errorMessage) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    // Validations

    private String generateFullTitle(String title, String reference) {
        if (!ValidationUtil.hasText(title) || !ValidationUtil.hasText(reference))
            return null;

        return String.format("[%s] %s", reference, title);
    }
}
