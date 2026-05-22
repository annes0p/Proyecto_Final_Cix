package com.example.cixoil.model;

import com.example.cixoil.enums.FuelType;
import com.example.cixoil.enums.TransmissionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "vehicle_model")
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle_model")
    private Long id;

    @JoinColumn(name = "id_vehicle_brand")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleBrand vehicleBrand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "horse_power")
    private Integer horsePower;

    @Column(name = "motor_cc")
    private Integer motorCC;

    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Column(name = "transmission_type")
    private TransmissionType transmissionType;

    @JoinColumn(name = "id_vehicle_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private VehicleType vehicleType;
}
