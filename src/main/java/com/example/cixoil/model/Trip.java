package com.example.cixoil.model;

import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trip")
    private Long id;

    @JoinColumn(name = "id_route")
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @JoinColumn(name = "id_origin_location")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location origin;

    @JoinColumn(name = "id_destination_location")
    @ManyToOne(fetch = FetchType.LAZY)
    private Location destination;

    @JoinColumn(name = "id_sale")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sale sale;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private ProgressStatus progressStatus = ProgressStatus.PENDING;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

    @Column(name = "observation", length = 500)
    private String observation;
}
