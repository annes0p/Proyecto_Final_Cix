package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.mapper.SelectMapper;
import com.example.cixoil.repository.IncidentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentCategoryService {

    private final IncidentCategoryRepository incidentCategoryRepository;
    private final SelectMapper selectMapper;

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return incidentCategoryRepository.findAll()
                .stream()
                .map(selectMapper::fromIncidentCategory)
                .toList();
    }
}
