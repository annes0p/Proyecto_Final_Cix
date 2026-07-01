package com.example.cixoil.dto.purchase;

import java.util.List;

public record PartialReceiveDTO(
        List<PartialReceiveItemDTO> items
) {}