package com.example.cixoil.repository;

import com.example.cixoil.model.ProductRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRecommendationRepository extends JpaRepository<ProductRecommendation, Long> {
}
