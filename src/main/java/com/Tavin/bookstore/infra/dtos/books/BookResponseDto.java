package com.Tavin.bookstore.infra.dtos.books;

import com.Tavin.bookstore.infra.dtos.authors.AuthorResponseDto;
import com.Tavin.bookstore.model.GenderModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDto(UUID idAuthor,
                              String title,
                              LocalDate publicationDate,
                              GenderModel gender,
                              BigDecimal price,
                              AuthorResponseDto author) {
}
