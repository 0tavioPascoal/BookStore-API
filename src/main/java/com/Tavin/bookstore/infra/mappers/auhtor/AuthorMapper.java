package com.Tavin.bookstore.infra.mappers.auhtor;

import com.Tavin.bookstore.infra.dtos.authors.AuthorResponseDto;
import com.Tavin.bookstore.infra.dtos.authors.AuthorsRequestDto;
import com.Tavin.bookstore.model.AuthorModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorModel authorModelMapper(AuthorsRequestDto authorsRequestDto);

    AuthorResponseDto authorResponseDtoMapper(AuthorModel authorModel);
}
