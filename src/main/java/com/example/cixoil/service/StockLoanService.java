package com.example.cixoil.service;

import com.example.cixoil.dto.stockloan.StockLoanDTO;
import com.example.cixoil.dto.stockloan.StockLoanSaveDTO;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.StockLoanMapper;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.Product;
import com.example.cixoil.model.StockLoan;
import com.example.cixoil.repository.ClientRepository;
import com.example.cixoil.repository.ProductRepository;
import com.example.cixoil.repository.StockLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockLoanService {

    private final StockLoanRepository stockLoanRepository;
    private final StockLoanMapper stockLoanMapper;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<StockLoanDTO> findAll() {
        return stockLoanRepository.findAll()
                .stream().map(stockLoanMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public StockLoanDTO getById(Long id) {
        StockLoan stockLoan = requireStockLoanById(id, "Préstamo no encontrado");
        return stockLoanMapper.toDTO(stockLoan);
    }

    @Transactional
    public StockLoanDTO create(StockLoanSaveDTO dto) {
        Client client = requireClientById(dto.idClient(), "No se encontró cliente");
        Product product = requireProductById(dto.idProduct(), "No se encontró producto");

        // TODO: Manejar estados por endpoint

        StockLoan created = StockLoan.builder()
                .quantityLoaned(dto.quantityLoaned())
                .quantityRemaining(dto.quantityRemaining())
                .loanStatus(dto.loanStatus())
                .client(client)
                .product(product)
                .build();

        return stockLoanMapper.toDTO(stockLoanRepository.save(created));
    }

    @Transactional
    public StockLoanDTO update(StockLoanSaveDTO dto, Long id) {
        StockLoan existent = requireStockLoanById(id, "Préstamo no encontrado para actualizar");

        Client client = requireClientById(dto.idClient(), "No se encontró cliente");
        Product product = requireProductById(dto.idProduct(), "No se encontró producto");

        existent.setQuantityLoaned(dto.quantityLoaned());
        existent.setQuantityRemaining(dto.quantityRemaining());
        existent.setLoanStatus(dto.loanStatus());
        existent.setClient(client);
        existent.setProduct(product);

        return stockLoanMapper.toDTO(stockLoanRepository.save(existent));
    }

    // Require

    private StockLoan requireStockLoanById(Long id, String errorMessage) {
        return stockLoanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Client requireClientById(Long id, String errorMessage) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Product requireProductById(Long id, String errorMessage) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
