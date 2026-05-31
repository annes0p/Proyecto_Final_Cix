package com.example.cixoil.service;

import com.example.cixoil.dto.vehicleunit.VehicleUnitDTO;
import com.example.cixoil.dto.vehicleunit.VehicleUnitSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.VehicleUnitMapper;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.VehicleModel;
import com.example.cixoil.model.VehicleUnit;
import com.example.cixoil.model.VehicleUseType;
import com.example.cixoil.repository.ClientRepository;
import com.example.cixoil.repository.VehicleModelRepository;
import com.example.cixoil.repository.VehicleUnitRepository;
import com.example.cixoil.repository.VehicleUseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleUnitService {

    private final VehicleUnitRepository vehicleUnitRepository;
    private final VehicleUnitMapper vehicleUnitMapper;
    private final VehicleModelRepository vehicleModelRepository;
    private final ClientRepository clientRepository;
    private final VehicleUseTypeRepository vehicleUseTypeRepository;

    @Transactional(readOnly = true)
    public List<VehicleUnitDTO> findNotDeleted() {
        return vehicleUnitRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(vehicleUnitMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public VehicleUnitDTO getById(Long id) {
        VehicleUnit vehicleUnit = requireVehicleUnitById(id, "Unidad vehicular no encontrada");
        return vehicleUnitMapper.toDTO(vehicleUnit);
    }

    @Transactional
    public VehicleUnitDTO create(VehicleUnitSaveDTO dto) {
        VehicleModel vehicleModel = requireVehicleModelById(dto.idVehicleModel(),
                "No se encontró modelo");
        Client client = requireClientById(dto.idClient(),
                "No se encontró cliente");
        VehicleUseType vehicleUseType = requireVehicleUseTypeById(dto.idVehicleUseType(),
                "No se encontró tipo de uso");

        VehicleUnit created = VehicleUnit.builder()
                .vehicleModel(vehicleModel)
                .client(client)
                .vehicleUseType(vehicleUseType)
                .plate(dto.plate())
                .color(dto.color())
                .build();

        return vehicleUnitMapper.toDTO(vehicleUnitRepository.save(created));
    }

    @Transactional
    public VehicleUnitDTO update(VehicleUnitSaveDTO dto, Long id) {
        VehicleUnit existent = requireVehicleUnitById(id, "Unidad vehicular no encontrada para actualizar");

        VehicleModel vehicleModel = requireVehicleModelById(dto.idVehicleModel(),
                "No se encontró modelo");
        Client client = requireClientById(dto.idClient(),
                "No se encontró cliente");
        VehicleUseType vehicleUseType = requireVehicleUseTypeById(dto.idVehicleUseType(),
                "No se encontró tipo de uso");

        existent.setVehicleModel(vehicleModel);
        existent.setClient(client);
        existent.setVehicleUseType(vehicleUseType);
        existent.setPlate(dto.plate());
        existent.setColor(dto.color());

        return vehicleUnitMapper.toDTO(vehicleUnitRepository.save(existent));
    }

    @Transactional
    public VehicleUnitDTO toggleStatus(Long id) {
        VehicleUnit existent = requireVehicleUnitById(id, "No se encontró vehicleUnite para cambiar estada");
        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return vehicleUnitMapper.toDTO(vehicleUnitRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        VehicleUnit existent = requireVehicleUnitById(id, "No se encontró vehicleUnite para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        vehicleUnitRepository.save(existent);
    }

    // Require

    private VehicleUnit requireVehicleUnitById(Long id, String errorMessage) {
        return vehicleUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private VehicleModel requireVehicleModelById(Long id, String errorMessage) {
        return vehicleModelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Client requireClientById(Long id, String errorMessage) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private VehicleUseType requireVehicleUseTypeById(Long id, String errorMessage) {
        return vehicleUseTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
