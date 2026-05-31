package com.example.cixoil.controller;

import com.example.cixoil.dto.client.ClientDTO;
import com.example.cixoil.dto.client.ClientSaveDTO;
import com.example.cixoil.enums.Status;
import com.example.cixoil.service.ClientService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientSaveDTO dto) {
        ClientDTO created = clientService.create(dto);
        return ResponseUtil.ok("Cliente creado correctamente", created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClientSaveDTO dto, @PathVariable Long id) {
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
