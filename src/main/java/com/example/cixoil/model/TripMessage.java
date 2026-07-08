package com.example.cixoil.model;

import com.example.cixoil.enums.MessageSender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Chat simple (no chatbot) entre el cliente y el personal de CIXOIL sobre
 * un envio puntual (Trip). Independiente de Trip.java (solo guarda el id,
 * sin relacion JPA), siguiendo el mismo criterio de aislamiento que
 * TripLocation, para no interferir con otros cambios sobre Trip.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "trip_message")
public class TripMessage extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trip_message")
    private Long id;

    @Column(name = "id_trip", nullable = false)
    private Long idTrip;

    @Enumerated(EnumType.STRING)
    @Column(name = "sender", length = 20)
    private MessageSender sender;

    @Column(name = "sender_name", length = 100)
    private String senderName;

    @Column(name = "content", length = 500)
    private String content;
}
