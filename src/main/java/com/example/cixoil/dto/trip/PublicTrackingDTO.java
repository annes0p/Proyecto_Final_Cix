package com.example.cixoil.dto.trip;

/**
 * DTO publico, sin datos sensibles, para que un cliente sin cuenta en el
 * sistema pueda ver el estado de la entrega de su pedido a traves de un
 * link de seguimiento.
 */
public record PublicTrackingDTO(
        Long idTrip,
        String routeDate,
        String origin,
        String destination,
        String progressStatus,
        String startTime,
        String endTime,
        String clienteNombre,
        Double latitude,
        Double longitude,
        String ubicacionActualizada,
        Integer deliveryRating
) {
}
