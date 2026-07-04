package com.example.cixoil.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cixoil.dto.client.ClientSaveDTO;
import com.example.cixoil.dto.publicsale.PublicClientIncidentDTO;
import com.example.cixoil.dto.publicsale.PublicClientLookupDTO;
import com.example.cixoil.dto.publicsale.PublicClientOrderDTO;
import com.example.cixoil.dto.publicsale.PublicIncidentReportRequestDTO;
import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.enums.Priority;
import com.example.cixoil.model.Client;
import com.example.cixoil.model.Incident;
import com.example.cixoil.model.IncidentCategory;
import com.example.cixoil.model.IncidentType;
import com.example.cixoil.model.Trip;
import com.example.cixoil.repository.ClientRepository;
import com.example.cixoil.repository.IncidentRepository;
import com.example.cixoil.repository.TripRepository;
import com.example.cixoil.utils.IncidentTokenUtil;

import lombok.RequiredArgsConstructor;

/**
 * Servicio del "portal del cliente" (buscar por DNI/RUC): agrupa sus
 * pedidos con seguimiento en vivo y sus incidencias, y permite que el
 * propio cliente reporte una incidencia nueva. Aislado de
 * SaleService/TripService/IncidentService "core" para no interferir
 * con esos flujos internos (mismo criterio que PublicSaleService).
 */
@Service
@RequiredArgsConstructor
public class PublicClientPortalService {

    private static final long CATEGORIA_CLIENTE_ID = 4L;

    private final TripRepository tripRepository;
    private final TrackingService trackingService;
    private final IncidentRepository incidentRepository;
    private final IncidentTypeService incidentTypeService;
    private final IncidentCategoryService incidentCategoryService;
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final IncidentTokenUtil incidentTokenUtil;

    // Usado por la tienda y el formulario de incidencias para
    // reconocer a un cliente que ya compro antes y no pedirle de
    // nuevo nombre/telefono/direccion.
    @Transactional(readOnly = true)
    public PublicClientLookupDTO buscarClienteExistente(String docNumber) {
        return clientRepository.findByDocNumber(docNumber)
                .map(c -> new PublicClientLookupDTO(
                        true,
                        c.getName(),
                        c.getFatherLastName(),
                        c.getMotherLastName(),
                        c.getDocumentType() != null ? c.getDocumentType().name() : null,
                        c.getPhoneNumber(),
                        c.getEmail(),
                        c.getAddress()
                ))
                .orElse(new PublicClientLookupDTO(false, null, null, null, null, null, null, null));
    }

    @Transactional(readOnly = true)
    public List<PublicClientOrderDTO> buscarPedidos(String docNumber) {
        List<Trip> trips = tripRepository.findBySale_Client_DocNumberOrderByIdDesc(docNumber);

        return trips.stream().map(trip -> new PublicClientOrderDTO(
                trip.getId(),
                trip.getRoute() != null && trip.getRoute().getRouteDate() != null
                        ? trip.getRoute().getRouteDate().toString()
                        : null,
                trip.getOrigin() != null ? trip.getOrigin().getName() : null,
                trip.getDestination() != null ? trip.getDestination().getName() : null,
                trip.getProgressStatus() != null ? trip.getProgressStatus().name() : null,
                trackingService.generarToken(trip.getId())
        )).toList();
    }

    @Transactional(readOnly = true)
    public List<PublicClientIncidentDTO> buscarIncidencias(String docNumber) {
        List<Incident> incidents = incidentRepository.findByClient_DocNumberOrderByIdDesc(docNumber);

        return incidents.stream().map(incident -> {
            boolean puedeCalificar =
                    (incident.getIncidentStatus() == IncidentStatus.RESOLVED
                            || incident.getIncidentStatus() == IncidentStatus.CLOSED)
                            && incident.getRating() == null;

            return new PublicClientIncidentDTO(
                    incident.getId(),
                    incident.getTitle(),
                    incident.getIncidentType() != null ? incident.getIncidentType().getName() : null,
                    incident.getIncidentStatus() != null ? incident.getIncidentStatus().name() : null,
                    incident.getPriority() != null ? incident.getPriority().name() : null,
                    incident.getDescription(),
                    incident.getResolutionNote(),
                    incident.getRating(),
                    incident.getCreatedAt() != null
                            ? incident.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                            : null,
                    incident.getResolvedAt() != null
                            ? incident.getResolvedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                            : null,
                    puedeCalificar ? incidentTokenUtil.generate(incident.getId()) : null
            );
        }).toList();
    }

    @Transactional
    public PublicClientIncidentDTO reportarIncidencia(PublicIncidentReportRequestDTO dto) {
        Client client = clientService.findOrCreateEntity(construirClienteDto(dto));

        IncidentType incidentType = incidentTypeService.requireIncidentTypeById(
                dto.idIncidentType(), "No se encontró el tipo de incidencia");
        IncidentCategory incidentCategory = incidentCategoryService.requireIncidentCategoryById(
                CATEGORIA_CLIENTE_ID, "No se encontró la categoría de incidencia");

        Incident created = Incident.builder()
                .title(dto.title())
                .description(dto.description())
                .incidentType(incidentType)
                .incidentCategory(incidentCategory)
                .reference(client.getName() + " " + (client.getFatherLastName() != null ? client.getFatherLastName() : ""))
                .reportedBy(client.getName())
                .priority(Priority.MEDIUM)
                .client(client)
                .build();

        incidentRepository.save(created);

        return new PublicClientIncidentDTO(
                created.getId(),
                created.getTitle(),
                incidentType.getName(),
                created.getIncidentStatus().name(),
                created.getPriority().name(),
                created.getDescription(),
                null,
                null,
                null,
                null,
                null
        );
    }

    private ClientSaveDTO construirClienteDto(PublicIncidentReportRequestDTO dto) {
        return new ClientSaveDTO(
                dto.name(),
                dto.fatherLastName(),
                dto.motherLastName(),
                dto.documentType(),
                dto.docNumber(),
                dto.phoneNumber(),
                dto.email(),
                null,
                dto.address(),
                false
        );
    }
}
