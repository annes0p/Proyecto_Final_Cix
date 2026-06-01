package com.example.cixoil.model;

import com.example.cixoil.enums.StockMovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "stock_movement")
public class StockMovement extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock_movement")
    private Long id;

    @JoinColumn(name = "id_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "initial_stock")
    private Long initialStock;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "final_stock")
    private Long finalStock;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private StockMovementType stockMovementType;

    @Builder.Default
    @Column(name = "movement_date")
    private LocalDateTime movementDate = LocalDateTime.now();
}
