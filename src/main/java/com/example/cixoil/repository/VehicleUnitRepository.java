package com.example.cixoil.repository;

import com.example.cixoil.model.VehicleUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleUnitRepository extends JpaRepository<VehicleUnit, Long> {
    List<VehicleUnit> findAllByStatusNot(Integer status);
}
