package com.example.cixoil.dto.sale;

import java.time.LocalDateTime;
import java.util.List;

import com.example.cixoil.dto.saledetail.SaleDetailSaveDTO;
import com.example.cixoil.enums.PaymentMethod;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.enums.VoucherType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SaleSaveDTO(
        LocalDateTime saleDate,

        @NotNull(message = "El tipo de comprobante es obligatorio")
        VoucherType voucherType,

        String series,

        @NotNull(message = "El método de pago es obligatorio")
        PaymentMethod paymentMethod,

        TransactionStatus transactionStatus,

        @NotNull(message = "El cliente es obligatorio")
        Long idClient,

        @NotNull(message = "El usuario es obligatorio")
        Long idUser,

        @NotEmpty(message = "La venta debe tener al menos un detalle")
        @Valid
        List<SaleDetailSaveDTO> details
) {
}
