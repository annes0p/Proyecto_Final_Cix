package com.example.cixoil.mapper;

import com.example.cixoil.dto.client.ClientDTO;
import com.example.cixoil.dto.client.ClientRefDTO;
import com.example.cixoil.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO(Client client);

    ClientRefDTO toRefDTO(Client client);
}
