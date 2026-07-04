package com.example.cixoil.service;

import com.example.cixoil.dto.trip.PublicTrackingDTO;
import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.Trip;
import com.example.cixoil.model.TripLocation;
import com.example.cixoil.repository.TripLocationRepository;
import com.example.cixoil.repository.TripRepository;
import com.example.cixoil.utils.TrackingTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Servicio nuevo e independiente de TripService, pensado para no cruzarse
 * con los cambios que Jaime tiene pendientes en Trip (observaciones).
 * Solo lee datos de Trip, no los modifica. La posicion GPS se guarda en
 * su propia tabla (TripLocation), tampoco toca Trip.
 */
@Service
@RequiredArgsConstructor
public class TrackingService {

    private static final ZoneId ZONA_PERU = ZoneId.of("America/Lima");

    private final TripRepository tripRepository;
    private final TripLocationRepository tripLocationRepository;
    private final TrackingTokenUtil trackingTokenUtil;

    @Transactional(readOnly = true)
    public String generarToken(Long idTrip) {
        requireTrip(idTrip);
        return trackingTokenUtil.generate(idTrip);
    }

    @Transactional
    public void actualizarUbicacion(Long idTrip, Double latitude, Double longitude) {
        requireTrip(idTrip);

        TripLocation ubicacion = tripLocationRepository.findById(idTrip)
                .orElse(TripLocation.builder().idTrip(idTrip).build());

        ubicacion.setLatitude(latitude);
        ubicacion.setLongitude(longitude);
        ubicacion.setUpdatedAt(LocalDateTime.now(ZONA_PERU));

        tripLocationRepository.save(ubicacion);
    }

    @Transactional(readOnly = true)
    public PublicTrackingDTO buscarPorToken(String token) {
        Long idTrip = trackingTokenUtil.verificarYExtraerTripId(token);
        if (idTrip == null) {
            throw new InvalidArgumentException("Enlace de seguimiento inválido");
        }

        Trip trip = requireTrip(idTrip);
        TripLocation ubicacion = tripLocationRepository.findById(idTrip).orElse(null);

        return new PublicTrackingDTO(
                trip.getId(),
                trip.getRoute() != null && trip.getRoute().getRouteDate() != null
                        ? trip.getRoute().getRouteDate().toString()
                        : null,
                trip.getOrigin() != null ? trip.getOrigin().getName() : null,
                trip.getDestination() != null ? trip.getDestination().getName() : null,
                trip.getProgressStatus() != null ? trip.getProgressStatus().name() : null,
                trip.getStartTime() != null ? trip.getStartTime().toString() : null,
                trip.getEndTime() != null ? trip.getEndTime().toString() : null,
                trip.getSale() != null && trip.getSale().getClient() != null
                        ? trip.getSale().getClient().getName()
                        : null,
                ubicacion != null ? ubicacion.getLatitude() : null,
                ubicacion != null ? ubicacion.getLongitude() : null,
                ubicacion != null && ubicacion.getUpdatedAt() != null
                        ? ubicacion.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        : null,
                trip.getDeliveryRating()
        );
    }

    /**
     * Calificacion de 1 a 5 de como llego la entrega, distinta de la
     * calificacion de incidencias (Incident.rating). Solo se puede calificar
     * un viaje ya completado (entregado), usando el mismo token publico de
     * seguimiento (no se crea un token ni pagina nueva).
     */
    @Transactional
    public PublicTrackingDTO calificarEntrega(String token, Integer rating) {
        Long idTrip = trackingTokenUtil.verificarYExtraerTripId(token);
        if (idTrip == null) {
            throw new InvalidArgumentException("Enlace de seguimiento inválido");
        }

        Trip trip = requireTrip(idTrip);

        if (trip.getProgressStatus() != ProgressStatus.COMPLETED)
            throw new BusinessException("Solo se puede calificar un pedido ya entregado");

        trip.setDeliveryRating(rating);
        tripRepository.save(trip);

        return buscarPorToken(token);
    }

    private Trip requireTrip(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el viaje"));
    }
}
