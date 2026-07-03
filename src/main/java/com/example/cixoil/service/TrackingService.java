package com.example.cixoil.service;

import com.example.cixoil.dto.trip.PublicTrackingDTO;
import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.Trip;
import com.example.cixoil.repository.TripRepository;
import com.example.cixoil.utils.TrackingTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio nuevo e independiente de TripService, pensado para no cruzarse
 * con los cambios que Jaime tiene pendientes en Trip (observaciones).
 * Solo lee datos de Trip, no los modifica.
 */
@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TripRepository tripRepository;
    private final TrackingTokenUtil trackingTokenUtil;

    @Transactional(readOnly = true)
    public String generarToken(Long idTrip) {
        requireTrip(idTrip);
        return trackingTokenUtil.generate(idTrip);
    }

    @Transactional(readOnly = true)
    public PublicTrackingDTO buscarPorToken(String token) {
        Long idTrip = trackingTokenUtil.verificarYExtraerTripId(token);
        if (idTrip == null) {
            throw new InvalidArgumentException("Enlace de seguimiento inválido");
        }

        Trip trip = requireTrip(idTrip);

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
                        : null
        );
    }

    private Trip requireTrip(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el viaje"));
    }
}
