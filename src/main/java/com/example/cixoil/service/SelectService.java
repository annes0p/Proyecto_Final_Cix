package com.example.cixoil.service;

import com.example.cixoil.dto.SelectDTO;
import com.example.cixoil.enums.*;
import com.example.cixoil.utils.SelectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SelectService {

    private final RoleService roleService;
    private final ProductBrandService productBrandService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final VehicleBrandService vehicleBrandService;
    private final VehicleTypeService vehicleTypeService;
    private final VehicleUseTypeService vehicleUseTypeService;
    private final IncidentTypeService incidentTypeService;
    private final IncidentCategoryService incidentCategoryService;

    public List<SelectDTO<String>> listDocumentTypes() {
        return SelectUtil.fromEnum(DocumentType.class);
    }

    public List<SelectDTO<String>> listFuelTypes() {
        return SelectUtil.fromEnum(FuelType.class);
    }

    public List<SelectDTO<String>> listPaymentMethods() {
        return SelectUtil.fromEnum(PaymentMethod.class);
    }

    public List<SelectDTO<String>> listPaymentTypes() {
        return SelectUtil.fromEnum(PaymentType.class);
    }

    public List<SelectDTO<String>> listMovementTypes() {
        return SelectUtil.fromEnum(StockMovementType.class);
    }

    public List<SelectDTO<String>> listTransmissionTypes() {
        return SelectUtil.fromEnum(TransmissionType.class);
    }

    public List<SelectDTO<String>> listVoucherTypes() {
        return SelectUtil.fromEnum(VoucherType.class);
    }

    public List<SelectDTO<String>> listPriorities(){
        return SelectUtil.fromEnum(Priority.class);
    }

    // TODO: Estandarizar todos los select de clase

    public List<SelectDTO<Long>> listRoles() {
        return roleService.listForSelect();
    }

    public List<SelectDTO<Long>> listProductBrands() {
        return productBrandService.listForSelect();
    }

    public List<SelectDTO<Long>> listLocations() {
        return locationService.listForSelect();
    }

    public List<SelectDTO<Long>> listCategories() {
        return categoryService.listForSelect();
    }

    public List<SelectDTO<Long>> listIncidentCategories() {
        return incidentCategoryService.listForSelect();
    }

    public List<SelectDTO<Long>> listVehicleBrand() {
        return vehicleBrandService.listForSelect();
    }

    public List<SelectDTO<Long>> listVehicleTypes() {
        return vehicleTypeService.listForSelect();
    }

    public List<SelectDTO<Long>> listVehicleUses() {
        return vehicleUseTypeService.listForSelect();
    }

    public List<SelectDTO<Long>> listIncidentTypes() {
        return incidentTypeService.listForSelect();
    }
}
