package com.example.cixoil.model;

import com.example.cixoil.enums.Priority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "product_recommendation")
public class ProductRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_recommendation")
    private Long id;

    @JoinColumn(name = "id_vehicle_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleUseType vehicleUseType;

    @JoinColumn(name = "id_vehicle_model")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleModel vehicleModel;

    @JoinColumn(name = "id_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
