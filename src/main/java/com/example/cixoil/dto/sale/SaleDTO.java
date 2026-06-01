package com.example.cixoil.dto.sale;

import com.example.cixoil.dto.client.ClientRefDTO;
import com.example.cixoil.dto.saledetail.SaleDetailDTO;
import com.example.cixoil.dto.user.UserRefDTO;
import com.example.cixoil.enums.PaymentMethod;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.enums.VoucherType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleDTO(
        LocalDateTime saleDate,
        VoucherType voucherType,
        String series,
        String number,
        BigDecimal subtotal,
        BigDecimal taxAmount,
        BigDecimal total,
        PaymentMethod paymentMethod,
        TransactionStatus transactionStatus,
        ClientRefDTO client,
        UserRefDTO user,
        List<SaleDetailDTO> details
) {
}
