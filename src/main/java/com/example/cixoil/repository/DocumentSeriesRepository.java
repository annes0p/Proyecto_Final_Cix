package com.example.cixoil.repository;

import com.example.cixoil.model.DocumentSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentSeriesRepository extends JpaRepository<DocumentSeries, Long> {
    Optional<DocumentSeries> findBySeries(String series);
}
