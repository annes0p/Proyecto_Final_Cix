package com.example.cixoil.repository;

import com.example.cixoil.enums.Status;
import com.example.cixoil.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct_Id(Long id);

    List<Inventory> findAllByProduct_StatusNot(Integer status);
}
