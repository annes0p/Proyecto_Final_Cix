package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.dto.location.LocationDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.LocationMapper;
import com.example.cixoil.model.Location;
import com.example.cixoil.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Transactional(readOnly = true)
    public List<LocationDTO> findAll() {
        return locationRepository.findAll()
                .stream().map(locationMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public LocationDTO getById(Long id) {
        return locationRepository.findById(id).map(locationMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return locationRepository.findAll()
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }

    // Require

    public Location requireLocationById(Long id, String errorMessage) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
