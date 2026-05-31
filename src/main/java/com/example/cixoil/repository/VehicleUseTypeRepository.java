package com.example.cixoil.repository;

import com.example.cixoil.model.VehicleUseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleUseTypeRepository extends JpaRepository<VehicleUseType, Long> {
}
