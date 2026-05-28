package com.example.cixoil.service;

import com.example.cixoil.dto.trip.*;
import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.BusinessException;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.TripMapper;
import com.example.cixoil.model.Location;
import com.example.cixoil.model.Route;
import com.example.cixoil.model.Trip;
import com.example.cixoil.repository.LocationRepository;
import com.example.cixoil.repository.RouteRepository;
import com.example.cixoil.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final RouteRepository routeRepository;
    private final RouteService routeService;
    private final LocationRepository locationRepository;

    //TODO: Estandarizar
    @Transactional(readOnly = true)
    public List<TripDTO> findNotDeleted() {
        return tripRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(tripMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public TripDTO getById(Long id) {
        Trip trip = requireTripById(id, "Viaje no encontrado");
        return tripMapper.toDTO(trip);
    }

    @Transactional
    public TripDTO create(TripSaveDTO dto) {
        Route route = requireRouteById(dto.idRoute(), "No se encontró ruta");
        Location origin = requireLocationById(dto.idOriginLocation(), "No se encontró lugar de origen");
        Location destination = requireLocationById(dto.idDestinationLocation(), "No se encontró lugar de destino");

        Trip created = Trip.builder()
                .route(route)
                .origin(origin)
                .destination(destination)
                .build();

        return tripMapper.toDTO(tripRepository.save(created));
    }

    @Transactional
    public TripDTO update(TripSaveDTO dto, Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje para actualizar");

        Route route = requireRouteById(dto.idRoute(), "No se encontró ruta");
        Location origin = requireLocationById(dto.idOriginLocation(), "No se encontró lugar de origen");
        Location destination = requireLocationById(dto.idDestinationLocation(), "No se encontró lugar de destino");

        existent.setRoute(route);
        existent.setOrigin(origin);
        existent.setDestination(destination);

        return tripMapper.toDTO(tripRepository.save(existent));
    }

    @Transactional
    public TripDTO updateTimes(TripTimeSaveDTO dto, Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje para actualizar la hora");

        existent.setStartTime(dto.startTime());
        existent.setEndTime(dto.endTime());

        return tripMapper.toDTO(tripRepository.save(existent));
    }
    //TODO: Se puede terminar con una hora menor a la de inicio? (terminar al día siguiente)

    @Transactional
    public TripDTO start(Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje para iniciar");

        validateCanStart(existent.getProgressStatus());

        existent.setStartTime(LocalTime.now());
        existent.setProgressStatus(ProgressStatus.IN_PROGRESS);

        routeService.recalculateProgress(existent.getRoute().getId());

        return tripMapper.toDTO(tripRepository.save(existent));
    }

    @Transactional
    public TripDTO complete(Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje para completar");

        validateCanComplete(existent.getProgressStatus());

        existent.setEndTime(LocalTime.now());
        existent.setProgressStatus(ProgressStatus.COMPLETED);

        routeService.recalculateProgress(existent.getRoute().getId());

        return tripMapper.toDTO(tripRepository.save(existent));
    }

    @Transactional
    public TripDTO cancel(Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje");

        validateCanCancel(existent.getProgressStatus());

        existent.setProgressStatus(ProgressStatus.CANCELED);

        return tripMapper.toDTO(tripRepository.save(existent));
    }

    @Transactional
    public TripDTO resume(Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje");

        validateCanResume(existent.getProgressStatus());
        existent.setProgressStatus(resolveProgress(existent));

        return tripMapper.toDTO(tripRepository.save(existent));
    }

    @Transactional
    public TripDTO toggleStatus(Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje");

        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return tripMapper.toDTO(tripRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Trip existent = requireTripById(id, "No se encontró viaje");
        existent.setStatus(Status.DELETED.getValue());
        tripRepository.save(existent);
    }

    // Require

    //TODO: Estandarizar

    /**
     * This method searches for a Trip. If it doesn't find it, throws an exception
     * to be handled.
     * @param id ID from the Trip that needs to exist
     * @param errorMessage Error message in case Trip doesn't exist
     * @return Existent Trip
     */
    private Trip requireTripById(Long id, String errorMessage) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Route requireRouteById(Long id, String errorMessage) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private Location requireLocationById(Long id, String errorMessage) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    // Resolve

    private ProgressStatus resolveProgress(Trip trip) {
        if (trip.getStartTime() == null && trip.getEndTime() == null)
            return ProgressStatus.PENDING;

        if (trip.getEndTime() != null)
            return ProgressStatus.COMPLETED;

        return ProgressStatus.IN_PROGRESS;
    }

    // Validate

    private void validateCanStart(ProgressStatus progress) {
        if (progress != ProgressStatus.PENDING)
            throw new BusinessException("Solo pueden iniciarse viajes pendientes");
    }

    private void validateCanComplete(ProgressStatus progress) {
        if (progress == ProgressStatus.CANCELED)
            throw new BusinessException("No se puede completar un viaje cancelado");
        if (progress == ProgressStatus.COMPLETED)
            throw new BusinessException("Ese viaje ya ha sido completado");
    }

    private void validateCanCancel(ProgressStatus progress) {
        if (progress == ProgressStatus.CANCELED)
            throw new BusinessException("Ese viaje ya ha sido cancelado");
        if (progress == ProgressStatus.COMPLETED)
            throw new BusinessException("No se puede cancelar un viaje completado");
    }

    private void validateCanResume(ProgressStatus progress) {
        if (progress != ProgressStatus.CANCELED)
            throw new BusinessException("Este viaje no ha sido cancelado");
    }
}
