package com.example.cixoil.model;

import com.example.cixoil.enums.ProgressStatus;
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

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private ProgressStatus progressStatus;
}
