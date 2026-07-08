package com.example.cixoil.repository;

import com.example.cixoil.model.TripMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripMessageRepository extends JpaRepository<TripMessage, Long> {
    List<TripMessage> findByIdTripOrderByCreatedAtAsc(Long idTrip);
}
