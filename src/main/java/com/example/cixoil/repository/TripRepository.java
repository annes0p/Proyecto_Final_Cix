package com.example.cixoil.repository;

import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByStatusNot(Integer status);

    List<Trip> findByProgressStatus(ProgressStatus progressStatus);

    List<Trip> findByRoute_Id(Long idRoute);

}
