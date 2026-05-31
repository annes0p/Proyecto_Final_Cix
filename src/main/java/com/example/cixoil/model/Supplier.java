package com.example.cixoil.model;

import com.example.cixoil.enums.DocumentType;
import com.example.cixoil.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "supplier")
public class Supplier extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplier")
    private Long id;

    @Column(name = "legal_name", nullable = false)
    private String legalName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType = DocumentType.RUC;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
           name = "supplier_product",
            joinColumns = @JoinColumn(name = "id_supplier"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    @JsonIgnoreProperties("supplier") // ?
    private List<Product> products = new ArrayList<>();

//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
}
