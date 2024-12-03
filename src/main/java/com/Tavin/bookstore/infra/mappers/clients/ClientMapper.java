package com.Tavin.bookstore.infra.mappers.clients;

import com.Tavin.bookstore.infra.dtos.clients.ClientsRequestDto;
import com.Tavin.bookstore.infra.dtos.clients.ClientsResponseDto;
import com.Tavin.bookstore.model.ClientsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientsModel clientsModelMapper(ClientsRequestDto requestDto);

    ClientsResponseDto clientsResponseModelMapper(ClientsModel clientsModel);
}
