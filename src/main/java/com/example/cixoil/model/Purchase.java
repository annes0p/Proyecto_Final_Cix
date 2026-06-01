package com.example.cixoil.model;

import com.example.cixoil.enums.ReceptionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private Long id;

    @JoinColumn(name = "id_supplier")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @CreationTimestamp
    @Column(name = "purchase_date", updatable = false)
    private LocalDate purchasedAt;

    @Column(name = "estimated_date")
    private LocalDate estimatedDeliveryAt;

    @Column(name = "delivery_date")
    private LocalDate deliveredAt;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "reception_status")
    private ReceptionStatus receptionStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "tr")

    @Builder.Default
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseDetail> details = new ArrayList<>();
}
