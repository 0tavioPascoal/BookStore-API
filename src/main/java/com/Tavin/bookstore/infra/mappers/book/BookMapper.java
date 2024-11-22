package com.Tavin.bookstore.infra.mappers.book;

import com.Tavin.bookstore.infra.dtos.books.BookRequestDto;
import com.Tavin.bookstore.infra.dtos.books.BookResponseDto;
import com.Tavin.bookstore.infra.mappers.auhtor.AuthorMapper;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java( authorRepository.findById(Dto.idAuthor()).orElse(null) )")

    public abstract  BookModel bookModelMapper(BookRequestDto Dto);

    public abstract BookResponseDto bookResponseDtoMapper(BookModel bookModel);

}
