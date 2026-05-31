package com.example.cixoil.model;

import com.example.cixoil.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "product")
public class Product extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "id_product_brand")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductBrand brand;

    @Column(name = "viscosity")
    private String viscosity;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

    @JoinColumn(name = "id_category")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createdAt;

    public String toInput() {
        return """
                Id: %d
                Nombre: %s
                Marca: %s
                Precio: %s
                Viscosidad: %s
                """.formatted(id, name, brand.getName(),
                price.toPlainString(), viscosity);
    }
}
