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
@Table(name = "document_series")
public class DocumentSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document_series")
    private Long id;

    @Column(name = "series")
    private String series;

    @Column(name = "current_number")
    private Long currentNumber;

    @Builder.Default
    @Column(name = "status")
    private Integer status = Status.ACTIVE.getValue();
}
