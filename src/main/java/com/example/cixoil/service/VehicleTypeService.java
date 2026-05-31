package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return vehicleTypeRepository.findAll()
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }
}
