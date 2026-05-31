package com.example.cixoil.dto.productrecommendation;

import com.example.cixoil.dto.product.ProductDTO;
import com.example.cixoil.dto.vehiclemodel.VehicleModelDTO;
import com.example.cixoil.dto.vehicleusetype.VehicleUseTypeDTO;
import com.example.cixoil.enums.Priority;

public record ProductRecommendationDTO(
        Long id,
        VehicleModelDTO vehicleModel,
        VehicleUseTypeDTO vehicleUseType,
        ProductDTO product,
        String reason,
        Priority priority
) {
}
