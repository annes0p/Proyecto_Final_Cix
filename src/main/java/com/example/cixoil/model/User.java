package com.example.cixoil.model;

import com.example.cixoil.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

    @JoinColumn(name = "id_role")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Column(name = "created_at") // Añadir fecha a los registros ya creados y luego poner null y no update
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
