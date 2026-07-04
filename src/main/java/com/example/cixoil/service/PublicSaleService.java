package com.example.cixoil.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cixoil.dto.client.ClientSaveDTO;
import com.example.cixoil.dto.publicsale.PublicProductDTO;
import com.example.cixoil.dto.publicsale.PublicSaleRequestDTO;
import com.example.cixoil.dto.publicsale.PublicSaleResponseDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.enums.VoucherType;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.Inventory;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.Sale;
import com.example.cixoil.model.SaleDetail;
import com.example.cixoil.repository.InventoryRepository;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio del portal público de ventas. Deliberadamente aislado de
 * SaleService/ClientService/ProductService (solo reutiliza sus
 * repositorios y, para clientes nuevos, ClientService.create para no
 * duplicar validaciones) para no interferir con esos flujos internos:
 * no genera correlativo de comprobante, no mueve stock ni requiere
 * usuario logueado. El pago (por ahora simulado, pendiente de conectar
 * una pasarela real como Culqi) se procesa en el checkout de la Tienda
 * antes de llegar aquí, por eso la venta ya se registra como COMPLETED
 * en vez de quedar PENDING: el cliente paga en el momento, no el
 * personal despues. El personal solo se encarga de coordinar el envio.
 */
@Service
@RequiredArgsConstructor
public class PublicSaleService {

    private final ProductRepository productRepository;
    private final ClientService clientService;
    private final SaleDetailService saleDetailService;
    private final SaleRepository saleRepository;
    private final StockMovementService stockMovementService;
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<PublicProductDTO> getCatalogo() {
        return productRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream()
                .filter(p -> p.getStatus() != null && p.getStatus().equals(Status.ACTIVE.getValue()))
                .map(this::toPublicProductDTO)
                .toList();
    }

    private static final ZoneId ZONA_PERU = ZoneId.of("America/Lima");

    @Transactional
    public PublicSaleResponseDTO crearVentaPublica(PublicSaleRequestDTO dto) {
        Client client = clientService.findOrCreateEntity(construirClienteDto(dto));

        List<SaleDetail> details = new ArrayList<>(dto.items()
                .stream().map(saleDetailService::build).toList());

        BigDecimal subtotal = sumar(details, SaleDetail::getSubtotal);
        BigDecimal taxAmount = sumar(details, SaleDetail::getTaxAmount);
        BigDecimal total = subtotal.add(taxAmount);

        Sale created = Sale.builder()
                .client(client)
                .user(null)
                .voucherType(VoucherType.SALE_NOTE)
                .transactionStatus(TransactionStatus.COMPLETED)
                .paymentMethod(dto.paymentMethod())
                .subtotal(subtotal)
                .taxAmount(taxAmount)
                .total(total)
                .build();

        details.forEach(created::addDetail);

        saleRepository.save(created);

        // Igual que una venta interna: descuenta stock real. Si algun
        // producto no tiene suficiente, StockMovementService.create()
        // lanza BusinessException y, al estar todo en la misma
        // transaccion, la venta tampoco se llega a guardar.
        List<StockMovementSaveDTO> movimientos = stockMovementService.generateSaleMovements(
                details, LocalDateTime.now(ZONA_PERU));
        stockMovementService.saveAll(movimientos);

        return new PublicSaleResponseDTO(
                created.getId(),
                total,
                "¡Pago aprobado! Tu pedido fue registrado, pronto coordinaremos la entrega."
        );
    }

    // Privados

    private ClientSaveDTO construirClienteDto(PublicSaleRequestDTO dto) {
        return new ClientSaveDTO(
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
    }

    private PublicProductDTO toPublicProductDTO(Product p) {
        Long stock = inventoryRepository.findByProduct_Id(p.getId())
                .map(Inventory::getStock)
                .orElse(0L);
        return new PublicProductDTO(
                p.getId(),
                p.getName(),
                p.getBrand() != null ? p.getBrand().getName() : null,
                p.getCategory() != null ? p.getCategory().getName() : null,
                p.getPrice(),
                p.getViscosity(),
                p.getDescription(),
                p.getImageUrl(),
                stock
        );
    }

    private BigDecimal sumar(List<SaleDetail> details, Function<SaleDetail, BigDecimal> function) {
        return details.stream()
                .map(function)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
