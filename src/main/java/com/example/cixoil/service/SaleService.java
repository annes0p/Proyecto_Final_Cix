package com.example.cixoil.service;

import com.example.cixoil.dto.sale.SaleDTO;
import com.example.cixoil.dto.sale.SaleSaveDTO;
import com.example.cixoil.dto.stockmovement.StockMovementSaveDTO;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.SaleMapper;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.Sale;
import com.example.cixoil.model.SaleDetail;
import com.example.cixoil.model.User;
import com.example.cixoil.repository.ClientRepository;
import com.example.cixoil.repository.SaleDetailRepository;
import com.example.cixoil.repository.SaleRepository;
import com.example.cixoil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final SaleDetailService saleDetailService;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final DocumentSeriesService documentSeriesService;
    private final StockMovementService stockMovementService;

    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        return saleRepository.findAll()
                .stream().map(saleMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public SaleDTO getById(Long id) {
        Sale sale = requireSaleById(id, "Venta no encontrada");
        return saleMapper.toDTO(sale);
    }

    @Transactional
    public SaleDTO create(SaleSaveDTO dto) {
        Client client = requireClientById(dto.idClient(),
                "Cliente no encontrado");
        User user = requireUserById(dto.idUser(),
                "Usuario no encontrado");

        String correlative = documentSeriesService.generateNextCorrelative(dto.series());

        List<SaleDetail> details = dto.details()
                .stream().map(saleDetailService::toEntity).toList();

        BigDecimal subtotal = getSumOf(details, SaleDetail::getSubtotal);
        BigDecimal taxAmount = getSumOf(details, SaleDetail::getTaxAmount);
        BigDecimal total = subtotal.add(taxAmount);

        Sale created = Sale.builder()
                .saleDate(dto.saleDate())
                .voucherType(dto.voucherType())
                .series(dto.series())
                .number(correlative)
                .subtotal(subtotal)
                .taxAmount(taxAmount)
                .total(total)
                .paymentMethod(dto.paymentMethod())
                .transactionStatus(dto.transactionStatus())
                .client(client)
                .user(user)
                .details(details)
                .build();

        List<StockMovementSaveDTO> movements = stockMovementService.generateSaleMovements(
                details, dto.saleDate());

        saleRepository.save(created);
        stockMovementService.saveAll(movements);

        return saleMapper.toDTO(created);
    }

    @Transactional
    public SaleDTO cancel(Long id) {
        Sale sale = requireSaleById(id, "Venta no encontrada para cancelar");

        if (sale.getTransactionStatus() == TransactionStatus.CANCELED)
            throw new BusinessException("Esta venta ya fue anulada");

        List<StockMovementSaveDTO> movements = stockMovementService.generateSaleCancelMovements(
                sale.getDetails(),
                sale.getSaleDate()); // ¿Mismo día?

        sale.setTransactionStatus(TransactionStatus.CANCELED);
        stockMovementService.saveAll(movements);

        return saleMapper.toDTO(sale);
    }

    // Require

    private Sale requireSaleById(Long id, String errorMessage) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Client requireClientById(Long id, String errorMessage) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private User requireUserById(Long id, String errorMessage) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    //

    private BigDecimal getSumOf(
            List<SaleDetail> details,
            Function<SaleDetail, BigDecimal> function
    ) {
        return details.stream()
                .map(function)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
