package com.example.cixoil.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "purchase_detail")
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase_detail")
    private Long id;

    @JoinColumn(name = "id_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "id_purchase")
    @ManyToOne(fetch = FetchType.LAZY)
    private Purchase purchase;

    @Column(name = "quantity")
    private Long quantity;

    @Builder.Default
    @Column(name = "received_quantity")
    private Long receivedQuantity = 0L;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "line_total")
    private BigDecimal lineTotal;
}
