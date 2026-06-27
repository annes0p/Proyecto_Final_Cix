package com.example.cixoil.model;

import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "incident")
public class Incident extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident")
    private Long id;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "id_incident_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private IncidentType incidentType;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority = Priority.LOW;

    @Column(name = "description", length = 500)
    private String description;

    @Builder.Default
    @Column(name = "reported_by")
    private String reportedBy = "VENDEDOR";

    @JoinColumn(name = "id_incident_category")
    @ManyToOne(fetch = FetchType.LAZY)
    private IncidentCategory incidentCategory;

    @Column(name = "reference")
    private String reference;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "incident_status")
    private IncidentStatus incidentStatus = IncidentStatus.OPEN; // TODO: Cambiar manejo de defaults

    @Column(name = "full_title")
    private String fullTitle;

    @Column(name = "resolution_note", length = 500)
    private String resolutionNote;
}
