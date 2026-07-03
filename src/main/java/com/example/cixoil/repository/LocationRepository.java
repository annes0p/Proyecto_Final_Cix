package com.example.cixoil.repository;

import com.example.cixoil.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByNormalizedName(String name);

    List<Location> findByNormalizedNameContainingIgnoreCaseAndStatusNot(String text, Integer status);
    List<Location> findByNormalizedNameContainingIgnoreCaseAndStatus(String text, Integer status);

    List<Location> findAllByStatusNot(Integer status);
}
