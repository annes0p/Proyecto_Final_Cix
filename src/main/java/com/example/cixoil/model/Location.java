package com.example.cixoil.model;

import com.example.cixoil.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "normalized_name", nullable = false, unique = true)
    private String normalizedName;

    // Coordenadas reales del lugar, usadas unicamente para simular en el
    // mapa el avance de una entrega (Trip) mientras esta IN_PROGRESS, sin
    // depender de que el vendedor comparta su GPS real. Nullable: los
    // lugares que ya existian no las tienen hasta correr la migracion.
    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();
}
