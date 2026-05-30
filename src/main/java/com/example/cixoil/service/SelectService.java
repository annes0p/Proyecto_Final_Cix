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

    // Posible abstracción usando interfaces {getValue()} para los enums

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

}
