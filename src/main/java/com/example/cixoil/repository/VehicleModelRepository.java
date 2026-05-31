package com.example.cixoil.repository;

import com.example.cixoil.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    List<VehicleModel> findAllByStatusNot(Integer status);
}
