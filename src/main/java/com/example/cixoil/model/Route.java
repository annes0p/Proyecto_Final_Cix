package com.example.cixoil.model;

import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "route")
public class Route extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route")
    private Long id;

    @JoinColumn(name = "id_user")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "route_date")
    private LocalDate routeDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "progress_status")
    private ProgressStatus progressStatus = ProgressStatus.PENDING;

    @JsonIgnoreProperties("route")
    @OneToMany(mappedBy = "route")
    private List<Trip> trips = new ArrayList<>();

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();

//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
}
