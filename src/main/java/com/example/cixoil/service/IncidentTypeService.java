package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.IncidentType;
import com.example.cixoil.repository.IncidentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentTypeService {

    private final IncidentTypeRepository incidentTypeRepository;

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return incidentTypeRepository.findAll()
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }

    // Require

    public IncidentType requireIncidentTypeById(Long id, String errorMessage) {
        return incidentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    public IncidentType requireIncidentTypeById(Long id) {
        return incidentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tipo de incidente"));
    }
}
