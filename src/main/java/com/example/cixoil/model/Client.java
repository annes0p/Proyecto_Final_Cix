package com.example.cixoil.model;

import com.example.cixoil.enums.DocumentType;
import com.example.cixoil.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "client")
public class Client extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "f_last_name")
    private String fatherLastName;

    @Column(name = "m_last_name")
    private String motherLastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "id_location")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

    @Builder.Default
    @Column(name = "is_trusted", nullable = false)
    private boolean isTrusted = false;

//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
}
