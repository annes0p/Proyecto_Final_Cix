package com.example.cixoil.service;

import com.example.cixoil.dto.vehiclemodel.VehicleModelDTO;
import com.example.cixoil.dto.vehiclemodel.VehicleModelSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.VehicleModelMapper;
import com.example.cixoil.model.VehicleBrand;
import com.example.cixoil.model.VehicleModel;
import com.example.cixoil.model.VehicleType;
import com.example.cixoil.repository.VehicleBrandRepository;
import com.example.cixoil.repository.VehicleModelRepository;
import com.example.cixoil.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleModelService {

    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleModelMapper vehicleModelMapper;
    private final VehicleBrandRepository vehicleBrandRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Transactional(readOnly = true)
    public List<VehicleModelDTO> findNotDeleted() {
        return vehicleModelRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(vehicleModelMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public VehicleModelDTO getById(Long id) {
        VehicleModel vehicleModel = requireVehicleModelById(id, "Modelo vehicular no encontrado");
        return vehicleModelMapper.toDTO(vehicleModel);
    }

    @Transactional
    public VehicleModelDTO create(VehicleModelSaveDTO dto) {
        VehicleBrand vehicleBrand = requireVehicleBrandById(
                dto.idVehicleBrand(),
                "Marca de vehículo no encontrada"
        );
        VehicleType vehicleType = requireVehicleTypeById(
                dto.idVehicleType(),
                "Tipo de vehículo no encontrado"
        );

        VehicleModel created = VehicleModel.builder()
                .model(dto.model())
                .year(dto.year())
                .vehicleBrand(vehicleBrand)
                .vehicleType(vehicleType)
                .horsePower(dto.horsePower())
                .motorCC(dto.motorCC())
                .fuelType(dto.fuelType())
                .transmissionType(dto.transmissionType())
                .build();

        return vehicleModelMapper.toDTO(vehicleModelRepository.save(created));
    }

    @Transactional
    public VehicleModelDTO update(VehicleModelSaveDTO dto, Long id) {
        VehicleModel existent = requireVehicleModelById(id, "Modelo no encontrado para actualizar");

        VehicleBrand vehicleBrand = requireVehicleBrandById(
                dto.idVehicleBrand(),
                "Marca de vehículo no encontrada"
        );
        VehicleType vehicleType = requireVehicleTypeById(
                dto.idVehicleType(),
                "Tipo de vehículo no encontrado"
        );

        existent.setModel(dto.model());
        existent.setYear(dto.year());
        existent.setVehicleBrand(vehicleBrand);
        existent.setVehicleType(vehicleType);
        existent.setHorsePower(dto.horsePower());
        existent.setMotorCC(dto.motorCC());
        existent.setFuelType(dto.fuelType());
        existent.setTransmissionType(dto.transmissionType());

        return vehicleModelMapper.toDTO(vehicleModelRepository.save(existent));
    }

    @Transactional
    public VehicleModelDTO toggleStatus(Long id) {
        VehicleModel existent = requireVehicleModelById(id, "No se encontró modelo para cambiar estado");
        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return vehicleModelMapper.toDTO(vehicleModelRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        VehicleModel existent = requireVehicleModelById(id, "No se encontró modelo para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        vehicleModelRepository.save(existent);
    }

    // Require

    private VehicleModel requireVehicleModelById(Long id, String errorMessage) {
        return vehicleModelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private VehicleBrand requireVehicleBrandById(Long id, String errorMessage) {
        return vehicleBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private VehicleType requireVehicleTypeById(Long id, String errorMessage) {
        return vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
