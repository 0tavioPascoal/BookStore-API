package com.Tavin.bookstore.infra.dtos.authors;

import com.Tavin.bookstore.model.AuthorModel;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDto(UUID id,
                                String name,
                                LocalDate dateOfBirth,
                                String nationality) {

}
