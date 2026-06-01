package com.example.cixoil.repository;

import com.example.cixoil.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepository  extends JpaRepository<StockMovement, Long> {
}
