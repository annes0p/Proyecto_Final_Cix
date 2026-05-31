package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.repository.VehicleUseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleUseTypeService {

    private final VehicleUseTypeRepository vehicleUseTypeRepository;

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return vehicleUseTypeRepository.findAll()
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }
}
