package com.example.cixoil.repository;

import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByClient_IdAndTransactionStatusNot(Long idClient, TransactionStatus status);

    List<Sale> findAllByClient_Id(Long idClient);
}
