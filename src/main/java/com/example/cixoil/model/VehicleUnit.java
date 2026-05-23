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
@Table(name = "vehicle_unit")
public class VehicleUnit extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle_unit")
    private Long id;

    @JoinColumn(name = "id_vehicle_use_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleUseType vehicleUseType;

    @JoinColumn(name = "id_client")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @JoinColumn(name = "id_vehicle_model")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleModel vehicleModel;

    @Column(name = "color")
    private String color;

    @Column(name = "plate")
    private String plate;

    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
}
