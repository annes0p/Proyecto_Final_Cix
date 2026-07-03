package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.dto.location.LocationDTO;
import com.example.cixoil.dto.location.LocationSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.LocationMapper;
import com.example.cixoil.model.Location;
import com.example.cixoil.repository.LocationRepository;
import com.example.cixoil.utils.TextUtil;
import com.example.cixoil.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public List<LocationDTO> findAllNotDeleted() {
        return locationRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(locationMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public LocationDTO getById(Long id) {
        return locationRepository.findById(id).map(locationMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<LocationDTO> searchByNameLike(String name) {

        if (!ValidationUtil.hasText(name))
            return findAllNotDeleted();

        // Solo activos
        return locationRepository
                .findByNormalizedNameContainingIgnoreCaseAndStatus(name, Status.ACTIVE.getValue())
                .stream().map(locationMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<SelectDTO<Long>> listForSelect() {
        return locationRepository.findAll()
                .stream()
                .map(e -> new SelectDTO<>(e.getId(), e.getName()))
                .toList();
    }

    @Transactional
    public LocationDTO create(LocationSaveDTO dto) {
        String normalizedName = TextUtil.normalize(dto.name());

        Optional<Location> optLocation = locationRepository.findByNormalizedName(normalizedName);

        if (optLocation.isPresent())
            throw new BusinessException("Ya existe una localización con ese nombre");

        Location created = Location.builder()
                .name(dto.name())
                .normalizedName(normalizedName)
                .build();

        return locationMapper.toDTO(locationRepository.save(created));
    }

    @Transactional
    public LocationDTO update(LocationSaveDTO dto, Long id) {
        Location existent = requireNotDeletedLocationById(id,
                "No se encontró localización para actualizar");

        String normalizedName = TextUtil.normalize(dto.name());

        if (isDuplicated(normalizedName, id))
            throw new BusinessException("Ya existe una localización con ese nombre");

        existent.setName(dto.name());
        existent.setNormalizedName(normalizedName);

        return locationMapper.toDTO(locationRepository.save(existent));
    }

    @Transactional
    public LocationDTO toggle(Long id) {
        Location existent = requireNotDeletedLocationById(id,
                "No se encontró localización para alternar estado");

        existent.setStatus(Status.toggleByValue(existent.getStatus()));

        return locationMapper.toDTO(locationRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Location existent = requireLocationById(id,
                "No se encontró localización para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        locationRepository.save(existent);
    }

    // Require

    public Location requireLocationById(Long id, String errorMessage) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    public Location requireNotDeletedLocationById(Long id, String errorMessage) {
        return locationRepository.findById(id)
                .filter(l -> !Objects.equals(l.getStatus(), Status.DELETED.getValue()))
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    //

    private boolean isDuplicated(String normalizedName, Long id) {
        return locationRepository.findByNormalizedName(normalizedName)
                .filter(location -> !location.getId().equals(id))
                .isPresent();
    }
}
