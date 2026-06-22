package com.example.cixoil.controller;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.service.*;
import com.example.cixoil.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/selects")
public class SelectController {

    private final SelectService selectService;
    // TODO: Pasar los demás services al SelectService para centralizar
    private final RoleService roleService;
    private final ProductBrandService productBrandService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final VehicleBrandService vehicleBrandService;
    private final VehicleTypeService vehicleTypeService;
    private final VehicleUseTypeService vehicleUseTypeService;
    private final IncidentTypeService incidentTypeService;

    private final String successMessage = "Lista de %s obtenida correctamente";

    @GetMapping("/documents")
    public ResponseEntity<?> listDocumentTypes() {
        List<SelectDTO<String>> data = selectService.listDocumentTypes();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de documento"),
                data
        );
    }

    @GetMapping("/fuel")
    public ResponseEntity<?> listFuelTypes() {
        List<SelectDTO<String>> data = selectService.listFuelTypes();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de combustible"),
                data
        );
    }

    @GetMapping("/payment-methods")
    public ResponseEntity<?> listPaymentMethods() {
        List<SelectDTO<String>> data = selectService.listPaymentMethods();
        return ResponseUtil.ok(
                String.format(successMessage, "métodos de pago"),
                data
        );
    }

    @GetMapping("/payment-types")
    public ResponseEntity<?> listPaymentTypes() {
        List<SelectDTO<String>> data = selectService.listPaymentTypes();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de pago"),
                data
        );
    }

    @GetMapping("/stock-movements")
    public ResponseEntity<?> listMovementTypes() {
        List<SelectDTO<String>> data = selectService.listMovementTypes();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de movimiento"),
                data
        );
    }

    @GetMapping("/transmissions")
    public ResponseEntity<?> listTransmissionTypes() {
        List<SelectDTO<String>> data = selectService.listTransmissionTypes();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de transmisión"),
                data
        );
    }

    @GetMapping("/vouchers")
    public ResponseEntity<?> listVoucherTypes() {
        List<SelectDTO<String>> data = selectService.listVoucherTypes();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de comprobante"),
                data
        );
    }

    @GetMapping("/priorities")
    public ResponseEntity<?> listPriorities() {
        List<SelectDTO<String>> data = selectService.listPriorities();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de prioridad"),
                data
        );
    }

    @GetMapping("/roles")
    public ResponseEntity<?> listRoles() {
        List<SelectDTO<Long>> data = roleService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "roles"),
                data
        );
    }

    @GetMapping("/product-brands")
    public ResponseEntity<?> listProductBrands() {
        List<SelectDTO<Long>> data = productBrandService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "marcas de producto"),
                data
        );
    }

    @GetMapping("/locations")
    public ResponseEntity<?> listLocations() {
        List<SelectDTO<Long>> data = locationService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "lugares"),
                data
        );
    }

    @GetMapping("/categories")
    public ResponseEntity<?> listCategories() {
        List<SelectDTO<Long>> data = categoryService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "categorías"),
                data
        );
    }

    @GetMapping("/vehicle-brands")
    public ResponseEntity<?> listVehicleBrands() {
        List<SelectDTO<Long>> data = vehicleBrandService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "marcas de vehículo"),
                data
        );
    }

    @GetMapping("/vehicle-types")
    public ResponseEntity<?> listVehicleTypes() {
        List<SelectDTO<Long>> data = vehicleTypeService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de vehículos"),
                data
        );
    }

    @GetMapping("/vehicle-uses")
    public ResponseEntity<?> listVehicleUseTypes() {
        List<SelectDTO<Long>> data = vehicleUseTypeService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de uso de vehículo"),
                data
        );
    }

    @GetMapping("/incident-types")
    public ResponseEntity<?> listIncidentTypes() {
        List<SelectDTO<Long>> data = incidentTypeService.listForSelect();
        return ResponseUtil.ok(
                String.format(successMessage, "tipos de incidentes"),
                data
        );
    }

    // TODO: Faltan
}
