package com.example.cixoil.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cixoil.dto.client.ClientDTO;
import com.example.cixoil.dto.client.ClientSaveDTO;
import com.example.cixoil.dto.sale.SaleDTO;
import com.example.cixoil.dto.vehicleunit.VehicleUnitDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.ClientService;
import com.example.cixoil.utils.ResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<?> listNotDeleted() {
        List<ClientDTO> data = clientService.findNotDeleted();
        return ResponseUtil.ok("Clientes obtenidos correctamente", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ClientDTO existent = clientService.getById(id);
        return ResponseUtil.ok("Cliente obtenido correctamente", existent);
    }

    @GetMapping("/{id}/vehicles")
    public ResponseEntity<?> findVehicles(@PathVariable Long id) {
        List<VehicleUnitDTO> data = clientService.listClientVehicles(id);
        return ResponseUtil.ok("Vehículos del cliente obtenidos correctamente", data);
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<?> findPurchases(@PathVariable Long id) {
        List<SaleDTO> data = clientService.listClientPurchases(id);
        return ResponseUtil.ok("Compras del cliente obtenidas correctamente", data);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClientSaveDTO dto) {
        ClientDTO created = clientService.create(dto);
        return ResponseUtil.ok("Cliente creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ClientSaveDTO dto, @PathVariable Long id) {
        ClientDTO updated = clientService.update(dto, id);
        return ResponseUtil.ok("Cliente actualizado correctamente", updated);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Long id) {
        ClientDTO toggled = clientService.toggleStatus(id);
        String msg = toggled.status().equals(Status.ACTIVE.getValue()) ?
                "Cliente activado correctamente" : "Cliente desactivado correctamente";
        return ResponseUtil.ok(msg, toggled);
    }

    @PatchMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseUtil.ok("Cliente eliminado correctamente", null);
    }
}
