package com.example.cixoil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Guarda solo la ULTIMA posicion GPS conocida de un viaje (Trip), para el
 * mapa en vivo del cliente. Es una tabla nueva e independiente, sin
 * relacion JPA hacia Trip, justamente para no tocar Trip.java (que Jaime
 * tiene pendiente modificar con el campo "observaciones").
 *
 * El id de esta tabla ES el mismo id del Trip (no autogenerado): cada
 * viaje tiene como maximo una fila aqui, que se sobreescribe en cada
 * actualizacion de posicion.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "trip_location")
public class TripLocation {

    @Id
    @Column(name = "id_trip")
    private Long idTrip;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
