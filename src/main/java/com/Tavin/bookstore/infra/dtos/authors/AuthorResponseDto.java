package com.Tavin.bookstore.infra.dtos.authors;

import com.Tavin.bookstore.model.AuthorModel;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDto(UUID id,
                                String name,
                                LocalDate dateOfBirth,
                                String nationality) {

    public AuthorModel mappedAuthorsResponse(){
        AuthorModel author = new AuthorModel();
        author.setName(this.name());
        author.setDateofbirth(this.dateOfBirth());
        author.setNationality(this.nationality());
        return author;
    }
}