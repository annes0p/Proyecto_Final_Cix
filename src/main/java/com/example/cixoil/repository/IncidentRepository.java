package com.example.cixoil.repository;

import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findByIncidentStatusNot(IncidentStatus status);
    List<Incident> findByIncidentStatus(IncidentStatus status);
}
