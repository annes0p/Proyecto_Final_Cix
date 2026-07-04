package com.example.cixoil.dto.publicsale;

public record PublicClientOrderDTO(
        Long tripId,
        String routeDate,
        String origin,
        String destination,
        String progressStatus,
        String trackingToken
) {
}
