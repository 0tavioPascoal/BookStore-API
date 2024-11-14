package com.Tavin.bookstore.infra.dtos.books;

import com.Tavin.bookstore.model.GenderModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRequestDto(String title,
                             LocalDate publicationDate,
                             GenderModel gender,
                             BigDecimal price,
                             UUID idAuthor) {
}
