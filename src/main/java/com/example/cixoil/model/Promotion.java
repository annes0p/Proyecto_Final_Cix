package com.example.cixoil.model;

import com.example.cixoil.enums.Status;
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
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "id_trigger_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product triggerProduct;

    @Column(name = "trigger_quantity")
    private Long triggerQuantity;

    @JoinColumn(name = "id_bonus_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product bonusProduct;

    @Column(name = "start_month")
    private Integer startMonth;

    @Column(name = "start_day")
    private Integer startDay;

    @Column(name = "end_month")
    private Integer endMonth;

    @Column(name = "end_day")
    private Integer endDay;

    @JoinColumn(name = "id_promotion_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private PromotionType promotionType;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

    @Builder.Default
    @Column(name = "auto_activate")
    private Boolean autoActivate = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
