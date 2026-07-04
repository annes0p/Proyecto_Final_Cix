package com.example.cixoil.dto.sale;

import com.example.cixoil.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public record SalePaymentConfirmDTO(
        @NotNull(message = "El método de pago es obligatorio")
        PaymentMethod paymentMethod
) {
}
