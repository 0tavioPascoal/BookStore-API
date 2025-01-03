package com.Tavin.bookstore.infra.mappers;

import com.Tavin.bookstore.infra.dtos.clients.ClientRequestDto;
import com.Tavin.bookstore.infra.dtos.clients.ClientResponseDto;
import com.Tavin.bookstore.infra.mappers.book.BookMapper;
import com.Tavin.bookstore.model.ClientsModel;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class ClientMapper {

    @Autowired
    BookMapper bookMapper;

    public abstract ClientsModel clientsModelMapper(ClientRequestDto requestDto);

    public abstract ClientResponseDto clientResponseDtoMapper(ClientsModel clientModel);
 }
