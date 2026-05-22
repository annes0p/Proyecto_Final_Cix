package com.example.cixoil.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "stock_prediction")
public class StockPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock_prediction")
    private Long id;

    @JoinColumn(name = "id_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @CreationTimestamp
    @Column(name = "prediction_date", updatable = false)
    private LocalDateTime predictedAt;

    @Column(name = "current_stock")
    private Long currentStock;

    @Column(name = "daily_sales_avg")
    private BigDecimal dailySalesAvg;

    @Column(name = "days_period")
    private Integer daysPeriod;

    @Column(name = "stock_out_date")
    private LocalDateTime stockOutAt;
}
