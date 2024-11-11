package com.Tavin.bookstore.dtos.Authors;

import com.Tavin.bookstore.model.AuthorModel;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorsRequestDto(UUID id,
                                String name,
                                LocalDate dateOfBirth,
                                String nationality) {

    public AuthorModel mappedAuthors(){
        AuthorModel author = new AuthorModel();
        author.setName(this.name());
        author.setDateofbirth(this.dateOfBirth());
        author.setNationality(this.nationality());
        return author;
    }
}
