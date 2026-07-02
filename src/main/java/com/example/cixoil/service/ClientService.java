package com.example.cixoil.service;

import com.example.cixoil.dto.client.ClientDTO;
import com.example.cixoil.dto.client.ClientSaveDTO;
import com.example.cixoil.dto.sale.SaleDTO;
import com.example.cixoil.dto.vehicleunit.VehicleUnitDTO;
import com.example.cixoil.enums.DocumentType;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ClientMapper;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.Location;
import com.example.cixoil.repository.ClientRepository;
import com.example.cixoil.repository.LocationRepository;
import com.example.cixoil.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final LocationRepository locationRepository;
    private final VehicleUnitService vehicleUnitService;
    private final SaleService saleService;

    @Transactional(readOnly = true)
    public List<ClientDTO> findNotDeleted() {
        return clientRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(clientMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ClientDTO getById(Long id) {
        Client client = requireClientById(id, "Cliente no encontrado");
        return clientMapper.toDTO(client);
    }

    @Transactional(readOnly = true)
    public List<VehicleUnitDTO> listClientVehicles(Long id) {
        return vehicleUnitService.findAllVehicleUnitsByClientId(id);
    }

    @Transactional(readOnly = true)
    public List<SaleDTO> listClientPurchases(Long id) {
        return saleService.findSalesByClientId(id);
    }

    @Transactional
    public ClientDTO create(ClientSaveDTO dto) {

        validateClientData(dto, null);

        Client created = Client.builder()
                .name(dto.name())
                .fatherLastName(dto.fatherLastName())
                .motherLastName(dto.motherLastName())
                .documentType(dto.documentType())
                .docNumber(dto.docNumber())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .location(
                        dto.idLocation() == null ? null : requireLocationById(
                                dto.idLocation(),
                                "Lugar no encontrado"
                        )
                )
                .address(dto.address())
                .trusted(dto.trusted())
                .build();

        return clientMapper.toDTO(clientRepository.save(created));
    }

    @Transactional
    public ClientDTO update(ClientSaveDTO dto, Long id) {
        Client existent = requireClientById(id, "Cliente no encontrado para actualizar");

        validateClientData(dto, id);

        existent.setName(dto.name());
        existent.setFatherLastName(dto.fatherLastName());
        existent.setMotherLastName(dto.motherLastName());
        existent.setDocumentType(dto.documentType());
        existent.setDocNumber(dto.docNumber());
        existent.setPhoneNumber(dto.phoneNumber());
        existent.setEmail(dto.email());
        existent.setLocation(
                dto.idLocation() == null ? null : requireLocationById(
                        dto.idLocation(),
                        "Lugar no encontrado"
                )
        );
        existent.setAddress(dto.address());
        existent.setTrusted(dto.trusted());

        return clientMapper.toDTO(clientRepository.save(existent));
    }

    @Transactional
    public ClientDTO toggleStatus(Long id) {
        Client existent = requireClientById(id, "No se encontró cliente para cambiar estado");
        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return clientMapper.toDTO(clientRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Client existent = requireClientById(id, "No se encontró cliente para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        clientRepository.save(existent);
    }

    // Require

    private Client requireClientById(Long id, String errorMessage) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Location requireLocationById(Long id, String errorMessage) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    // Validaciones

    private void validateClientData(ClientSaveDTO dto, Long idExcluido) {
        if (!ValidationUtil.hasText(dto.name()))
            throw new BusinessException("El nombre del cliente es obligatorio");

        if (dto.documentType() == null)
            throw new BusinessException("El tipo de documento es obligatorio");

        if (!ValidationUtil.hasText(dto.docNumber()))
            throw new BusinessException("El número de documento es obligatorio");

        if (!dto.docNumber().matches("\\d+"))
            throw new BusinessException("El número de documento solo debe contener números");

        int largoEsperado = dto.documentType() == DocumentType.RUC ? 11 : 8;
        if (dto.docNumber().length() != largoEsperado)
            throw new BusinessException(
                    "El " + dto.documentType().getValue() + " debe tener " + largoEsperado + " dígitos"
            );

        if (ValidationUtil.hasText(dto.email()) && !dto.email().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"))
            throw new BusinessException("El email no tiene un formato válido");

        clientRepository.findByDocNumber(dto.docNumber()).ifPresent(existing -> {
            if (idExcluido == null || !existing.getId().equals(idExcluido))
                throw new BusinessException("Ya existe un cliente registrado con ese número de documento");
        });
    }
}
