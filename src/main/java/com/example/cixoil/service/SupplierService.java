package com.example.cixoil.service;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.supplier.SupplierDTO;
import com.example.cixoil.dto.supplier.SupplierSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ProductMapper;
import com.example.cixoil.mapper.SupplierMapper;
import com.example.cixoil.model.Supplier;
import com.example.cixoil.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<SupplierDTO> findNotDeleted() {
        return supplierRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(supplierMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public SupplierDTO getById(Long id) {
        Supplier supplier = requireSupplierById(id, "Proveedor no encontrado");
        return supplierMapper.toDTO(supplier);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> listSupplierProducts(Long id) {
        Supplier existent = requireSupplierById(id, "Proveedor no encontrado");
        return existent.getProducts()
                .stream().map(productMapper::toDTO).toList();
    }

    @Transactional
    public SupplierDTO create(SupplierSaveDTO dto) {

        Supplier created = Supplier.builder()
                .legalName(dto.legalName())
                .documentType(dto.documentType())
                .docNumber(dto.docNumber())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .address(dto.address())
                .build();

        return supplierMapper.toDTO(supplierRepository.save(created));
    }

    @Transactional
    public SupplierDTO update(SupplierSaveDTO dto, Long id) {
        Supplier existent = requireSupplierById(id, "Proveedor no encontrado para actualizar");

        existent.setLegalName(dto.legalName());
        existent.setDocumentType(dto.documentType());
        existent.setDocNumber(dto.docNumber());
        existent.setPhoneNumber(dto.phoneNumber());
        existent.setEmail(dto.email());
        existent.setAddress(dto.address());

        return supplierMapper.toDTO(supplierRepository.save(existent));
    }

    @Transactional
    public SupplierDTO toggleStatus(Long id) {
        Supplier existent = requireSupplierById(id, "No se encontró suppliere para cambiar estado");
        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return supplierMapper.toDTO(supplierRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Supplier existent = requireSupplierById(id, "No se encontró proveedor para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        supplierRepository.save(existent);
    }

    // Require

    private Supplier requireSupplierById(Long id, String errorMessage) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
