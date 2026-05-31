package com.example.cixoil.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "vehicle_use_type")
public class VehicleUseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle_use_type")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    public String toInput() {
        return """
                - Tipo: %s
                - Descripción: %s
                """.formatted(name, description);
    }
}
