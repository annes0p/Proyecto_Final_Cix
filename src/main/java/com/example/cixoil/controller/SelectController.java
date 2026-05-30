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
    private final RoleService roleService;
    private final ProductBrandService productBrandService;
    private final LocationService locationService;
    private final CategoryService categoryService;
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

    // TODO: Faltan
}
