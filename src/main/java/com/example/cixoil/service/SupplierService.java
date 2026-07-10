package com.example.cixoil.service;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.supplier.SupplierDTO;
import com.example.cixoil.dto.supplier.SupplierSaveDTO;
import com.example.cixoil.enums.DocumentType;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.ProductMapper;
import com.example.cixoil.mapper.SupplierMapper;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.Supplier;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.SupplierRepository;
import com.example.cixoil.utils.ValidationUtil;
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
    private final ProductRepository productRepository;

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

        validateSupplierData(dto, null);

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

        validateSupplierData(dto, id);

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

    /**
     * Reemplaza la lista completa de productos que ofrece este proveedor.
     * Se usa desde la pantalla de Proveedores (checklist), no desde
     * Productos, para no duplicar la gestion en dos pantallas.
     */
    @Transactional
    public List<ProductDTO> updateProducts(Long id, List<Long> productIds) {
        Supplier existent = requireSupplierById(id, "Proveedor no encontrado");

        List<Product> productos = productIds == null || productIds.isEmpty()
                ? List.of()
                : productRepository.findAllById(productIds);

        existent.setProducts(productos);
        supplierRepository.save(existent);

        return productos.stream().map(productMapper::toDTO).toList();
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

    // Validaciones

    private void validateSupplierData(SupplierSaveDTO dto, Long idExcluido) {
        if (!ValidationUtil.hasText(dto.legalName()))
            throw new BusinessException("La razón social del proveedor es obligatoria");

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

        supplierRepository.findByDocNumber(dto.docNumber()).ifPresent(existing -> {
            if (idExcluido == null || !existing.getId().equals(idExcluido))
                throw new BusinessException("Ya existe un proveedor registrado con ese número de documento");
        });
    }
}
