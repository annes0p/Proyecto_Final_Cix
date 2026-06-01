package com.example.cixoil.mapper;

import com.example.cixoil.dto.stockloan.StockLoanDTO;
import com.example.cixoil.model.StockLoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockLoanMapper {
    StockLoanDTO toDTO(StockLoan stockLoan);
}
