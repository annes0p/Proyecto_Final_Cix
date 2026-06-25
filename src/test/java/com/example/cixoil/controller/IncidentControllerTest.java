package com.example.cixoil.controller;

import com.example.cixoil.annotation.ControllerTest;
import com.example.cixoil.dto.incident.IncidentDTO;
import com.example.cixoil.dto.incidentcategory.IncidentCategoryRefDTO;
import com.example.cixoil.dto.incidenttype.IncidentTypeRefDTO;
import com.example.cixoil.enums.IncidentStatus;
import com.example.cixoil.enums.Priority;
import com.example.cixoil.exception.ResourceNotFoundException;
import com.example.cixoil.service.IncidentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest(controllers = IncidentController.class)
class IncidentControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    @Test
    void shouldReturnIncidentById() throws Exception {
        IncidentDTO incident = new IncidentDTO(
                1L,
                "Test MVC",
                new IncidentTypeRefDTO(1L, "IncidentType"),
                Priority.HIGH,
                "Testing my MVC for IncidentController",
                "TESTER",
                new IncidentCategoryRefDTO(1L, "IncidentCategory"),
                "REF-100",
                IncidentStatus.OPEN,
                "[REF-100] Test MVC"
        );

        Mockito.when(incidentService.getById(1L))
                .thenReturn(incident);

        mockMvc.perform(get("/api/incidents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message")
                        .value("Incidente obtenido correctamente"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("Test MVC"))
                .andExpect(jsonPath("$.data.priority").value("HIGH"))
                .andExpect(jsonPath("$.data.incidentStatus").value("OPEN"));

        Mockito.verify(incidentService).getById(1L);
    }

    @Test
    void shouldReturn404WhenIncidentNotFound() throws Exception {
        Mockito.when(incidentService.getById(99L))
                .thenThrow(new ResourceNotFoundException("Incidente no encontrado"));

        mockMvc.perform(get("/api/incidents/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Incidente no encontrado"));
    }

    @Test
    void shouldReturn400WhenIdIsInvalid() throws Exception {
        mockMvc.perform(get("/api/incidents/abc"))
                .andExpect(status().isBadRequest());
    }
}
