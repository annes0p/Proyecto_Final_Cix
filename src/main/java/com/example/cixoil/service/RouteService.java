package com.example.cixoil.service;

import com.example.cixoil.dto.route.RouteDTO;
import com.example.cixoil.dto.route.RouteSaveDTO;
import com.example.cixoil.dto.trip.TripDTO;
import com.example.cixoil.enums.ProgressStatus;
import com.example.cixoil.enums.Status;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.mapper.RouteMapper;
import com.example.cixoil.mapper.TripMapper;
import com.example.cixoil.model.Route;
import com.example.cixoil.model.Trip;
import com.example.cixoil.model.User;
import com.example.cixoil.repository.RouteRepository;
import com.example.cixoil.repository.TripRepository;
import com.example.cixoil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final TripMapper tripMapper;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RouteDTO> findNotDeleted() {
        return routeRepository.findAllByStatusNot(Status.DELETED.getValue())
                .stream().map(routeMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public RouteDTO getById(Long id) {
        Route route = requireRouteById(id, "Ruta no encontrada");
        return routeMapper.toDTO(route);
    }

    @Transactional(readOnly = true)
    public List<TripDTO> findTripsByRouteIdNotDeleted(Long id) {
        requireRouteById(id, "No se encontró la ruta");
        return tripRepository.findByRoute_IdAndStatusNot(id, Status.DELETED.getValue())
                .stream().map(tripMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<RouteDTO> findPendingByUserId(Long id) {
        requireUserById(id, "No se encontró usuario");
        List<Route> pendingRoutes = routeRepository.findByUser_IdAndProgressStatus(id, ProgressStatus.PENDING);
        return pendingRoutes.stream().map(routeMapper::toDTO).toList();
    }

    @Transactional
    public RouteDTO create(RouteSaveDTO dto) {
        User user = requireUserById(dto.idUser(), "No se encontró ruta");

        Route created = Route.builder()
                .user(user)
                .routeDate(dto.routeDate())
                .build();

        return routeMapper.toDTO(routeRepository.save(created));
    }

    @Transactional
    public RouteDTO update(RouteSaveDTO dto, Long id) {
        Route existent = requireRouteById(id, "Ruta no encontrada para actualizar");

        User user = requireUserById(dto.idUser(), "Usuario no encontrado");

        existent.setUser(user);
        existent.setRouteDate(dto.routeDate());

        return routeMapper.toDTO(routeRepository.save(existent));
    }

    @Transactional
    public RouteDTO toggleStatus(Long id) {
        Route existent = requireRouteById(id, "No se encontró ruta para cambiar estado");
        existent.setStatus(
                existent.getStatus().equals(Status.ACTIVE.getValue()) ?
                        Status.INACTIVE.getValue() : Status.ACTIVE.getValue()
        );

        return routeMapper.toDTO(routeRepository.save(existent));
    }

    @Transactional
    public void delete(Long id) {
        Route existent = requireRouteById(id, "No se encontró ruta para eliminar");
        existent.setStatus(Status.DELETED.getValue());
        routeRepository.save(existent);
    }

    @Transactional
    public void recalculateProgress(Long id) {
        Route route = requireRouteById(id, "No se encontró ruta");

        List<Trip> trips = route.getTrips();

        if (trips.isEmpty()) {
            route.setProgressStatus(ProgressStatus.PENDING);
            return;
        }

        boolean allCanceled = true;
        boolean allResolved = true;
        boolean anyProgress = false;

        for (Trip trip : trips) {
            ProgressStatus progress = trip.getProgressStatus();

            if (progress != ProgressStatus.CANCELED) allCanceled = false;
            if (progress != ProgressStatus.COMPLETED && progress != ProgressStatus.CANCELED) allResolved = false;
            if (progress == ProgressStatus.IN_PROGRESS) anyProgress = true;
        }

        if (allCanceled) {
            route.setProgressStatus(ProgressStatus.CANCELED);
        } else if (allResolved) {
            route.setProgressStatus(ProgressStatus.COMPLETED);
        } else if (anyProgress) {
            route.setProgressStatus(ProgressStatus.IN_PROGRESS);
        } else {
            route.setProgressStatus(ProgressStatus.PENDING);
        }
    }

    // Require

    private Route requireRouteById(Long id, String errorMessage) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    private User requireUserById(Long id, String errorMessage) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
