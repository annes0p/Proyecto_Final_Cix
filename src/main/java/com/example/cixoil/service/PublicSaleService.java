package com.example.cixoil.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cixoil.dto.client.ClientSaveDTO;
import com.example.cixoil.dto.publicsale.PublicProductDTO;
import com.example.cixoil.dto.publicsale.PublicSaleRequestDTO;
import com.example.cixoil.dto.publicsale.PublicSaleResponseDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.enums.VoucherType;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.Sale;
import com.example.cixoil.model.SaleDetail;
import com.example.cixoil.repository.ClientRepository;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio del portal público de ventas. Deliberadamente aislado de
 * SaleService/ClientService/ProductService (solo reutiliza sus
 * repositorios y, para clientes nuevos, ClientService.create para no
 * duplicar validaciones) para no interferir con esos flujos internos:
 * no genera correlativo de comprobante, no mueve stock ni requiere
 * usuario logueado. La venta queda PENDING para que el personal la
 * revise, confirme el pago y recién ahí la procese como una venta
 * normal (boleta/factura) desde el sistema interno.
 */
@Service
@RequiredArgsConstructor
public class PublicSaleService {

    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final SaleDetailService saleDetailService;
    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public List<PublicProductDTO> getCatalogo() {
        return productRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream()
                .filter(p -> p.getStatus() != null && p.getStatus().equals(Status.ACTIVE.getValue()))
                .map(this::toPublicProductDTO)
                .toList();
    }

    @Transactional
    public PublicSaleResponseDTO crearVentaPublica(PublicSaleRequestDTO dto) {
        Client client = clientRepository.findByDocNumber(dto.docNumber())
                .orElseGet(() -> crearClienteNuevo(dto));

        List<SaleDetail> details = new ArrayList<>(dto.items()
                .stream().map(saleDetailService::build).toList());

        BigDecimal subtotal = sumar(details, SaleDetail::getSubtotal);
        BigDecimal taxAmount = sumar(details, SaleDetail::getTaxAmount);
        BigDecimal total = subtotal.add(taxAmount);

        Sale created = Sale.builder()
                .client(client)
                .user(null)
                .voucherType(VoucherType.SALE_NOTE)
                .transactionStatus(TransactionStatus.PENDING)
                .subtotal(subtotal)
                .taxAmount(taxAmount)
                .total(total)
                .build();

        details.forEach(created::addDetail);

        saleRepository.save(created);

        return new PublicSaleResponseDTO(
                created.getId(),
                total,
                "¡Pedido registrado! Nos pondremos en contacto contigo para confirmar el pago y la entrega."
        );
    }

    // Privados

    private Client crearClienteNuevo(PublicSaleRequestDTO dto) {
        ClientSaveDTO clientDto = new ClientSaveDTO(
                dto.name(),
                dto.fatherLastName(),
                dto.motherLastName(),
                dto.documentType(),
                dto.docNumber(),
                dto.phoneNumber(),
                dto.email(),
                null,
                dto.address(),
                false
        );

        Long idClienteCreado = clientService.create(clientDto).id();

        return clientRepository.findById(idClienteCreado)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo registrar el cliente"));
    }

    private PublicProductDTO toPublicProductDTO(Product p) {
        return new PublicProductDTO(
                p.getId(),
                p.getName(),
                p.getBrand() != null ? p.getBrand().getName() : null,
                p.getCategory() != null ? p.getCategory().getName() : null,
                p.getPrice(),
                p.getViscosity(),
                p.getDescription(),
                p.getImageUrl()
        );
    }

    private BigDecimal sumar(List<SaleDetail> details, Function<SaleDetail, BigDecimal> function) {
        return details.stream()
                .map(function)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
