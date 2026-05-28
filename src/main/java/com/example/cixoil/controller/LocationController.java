package com.example.cixoil.controller;

import com.example.cixoil.dto.location.LocationDTO;
import com.example.cixoil.service.LocationService;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<?> listAll() {
        List<LocationDTO> data = locationService.findAll();
        return ResponseUtil.ok("Lugares encontrados", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        LocationDTO existent = locationService.getById(id);
        return ResponseUtil.ok("Lugar obtenido correctamente", existent);
    }
}
