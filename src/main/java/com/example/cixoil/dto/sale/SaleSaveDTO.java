package com.example.cixoil.dto.sale;

import com.example.cixoil.dto.client.ClientRefDTO;
import com.example.cixoil.dto.saledetail.SaleDetailSaveDTO;
import com.example.cixoil.dto.user.UserRefDTO;
import com.example.cixoil.enums.PaymentMethod;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.enums.VoucherType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleSaveDTO(
        LocalDateTime saleDate,
        VoucherType voucherType,
        String series,
        PaymentMethod paymentMethod,
        TransactionStatus transactionStatus,
        Long idClient,
        Long idUser,
        List<SaleDetailSaveDTO> details
) {
}
