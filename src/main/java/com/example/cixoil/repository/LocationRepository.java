package com.example.cixoil.repository;

import com.example.cixoil.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByNormalizedName(String name);

    Optional<Location> findByNormalizedNameContains(String text);

    List<Location> findAllByStatusNot(Integer status);
}
