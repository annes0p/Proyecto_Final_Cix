package com.example.cixoil.repository;

import com.example.cixoil.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByStatusNot(Integer status);

    List<Supplier> findByLegalNameContainingIgnoreCase(String part);

    Optional<Supplier> findByDocNumber(String docNumber);
}
