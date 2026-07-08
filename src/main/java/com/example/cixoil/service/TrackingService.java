package com.example.cixoil.service;

import com.example.cixoil.dto.trip.PublicTrackingDTO;
import com.example.cixoil.dto.trip.TripMessageDTO;
import com.example.cixoil.enums.MessageSender;
import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.InvalidArgumentException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.model.Trip;
import com.example.cixoil.model.TripLocation;
import com.example.cixoil.model.TripMessage;
import com.example.cixoil.repository.TripLocationRepository;
import com.example.cixoil.repository.TripMessageRepository;
import com.example.cixoil.repository.TripRepository;
import com.example.cixoil.utils.TrackingTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private final TripMessageRepository tripMessageRepository;
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

        String startDateTime = null;
        if (trip.getRoute() != null && trip.getRoute().getRouteDate() != null && trip.getStartTime() != null) {
            startDateTime = LocalDateTime.of(trip.getRoute().getRouteDate(), trip.getStartTime())
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

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
                trip.getDeliveryRating(),
                trip.getOrigin() != null ? trip.getOrigin().getLatitude() : null,
                trip.getOrigin() != null ? trip.getOrigin().getLongitude() : null,
                trip.getDestination() != null ? trip.getDestination().getLatitude() : null,
                trip.getDestination() != null ? trip.getDestination().getLongitude() : null,
                startDateTime
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

    // Chat (no chatbot) entre cliente y personal sobre un envio puntual.
    // El cliente entra con el mismo token publico de seguimiento; el
    // personal entra autenticado desde el detalle de la ruta (Trip).

    @Transactional(readOnly = true)
    public List<TripMessageDTO> listarMensajesPorToken(String token) {
        Long idTrip = trackingTokenUtil.verificarYExtraerTripId(token);
        if (idTrip == null) {
            throw new InvalidArgumentException("Enlace de seguimiento inválido");
        }
        requireTrip(idTrip);
        return tripMessageRepository.findByIdTripOrderByCreatedAtAsc(idTrip)
                .stream().map(this::toMessageDTO).toList();
    }

    @Transactional
    public TripMessageDTO enviarMensajeCliente(String token, String content) {
        Long idTrip = trackingTokenUtil.verificarYExtraerTripId(token);
        if (idTrip == null) {
            throw new InvalidArgumentException("Enlace de seguimiento inválido");
        }
        Trip trip = requireTrip(idTrip);
        String nombreCliente = trip.getSale() != null && trip.getSale().getClient() != null
                ? trip.getSale().getClient().getName()
                : "Cliente";

        TripMessage mensaje = TripMessage.builder()
                .idTrip(idTrip)
                .sender(MessageSender.CLIENT)
                .senderName(nombreCliente)
                .content(content)
                .build();

        return toMessageDTO(tripMessageRepository.save(mensaje));
    }

    @Transactional(readOnly = true)
    public List<TripMessageDTO> listarMensajesPorTripId(Long idTrip) {
        requireTrip(idTrip);
        return tripMessageRepository.findByIdTripOrderByCreatedAtAsc(idTrip)
                .stream().map(this::toMessageDTO).toList();
    }

    @Transactional
    public TripMessageDTO enviarMensajeStaff(Long idTrip, String content, String nombreStaff) {
        requireTrip(idTrip);

        TripMessage mensaje = TripMessage.builder()
                .idTrip(idTrip)
                .sender(MessageSender.STAFF)
                .senderName(nombreStaff != null && !nombreStaff.isBlank() ? nombreStaff : "CIXOIL")
                .content(content)
                .build();

        return toMessageDTO(tripMessageRepository.save(mensaje));
    }

    private TripMessageDTO toMessageDTO(TripMessage m) {
        return new TripMessageDTO(
                m.getId(),
                m.getSender() != null ? m.getSender().name() : null,
                m.getSenderName(),
                m.getContent(),
                m.getCreatedAt() != null
                        ? m.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        : null
        );
    }

    private Trip requireTrip(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el viaje"));
    }
}
