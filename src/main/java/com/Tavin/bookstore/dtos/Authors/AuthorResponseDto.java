package com.Tavin.bookstore.dtos.Authors;

import com.Tavin.bookstore.model.AuthorModel;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorResponseDto(UUID id,
                                String name,
                                LocalDate dateofbirth,
                                String nationality) {

    public AuthorModel mappedAuthorsResponse(){
        AuthorModel author = new AuthorModel();
        author.setName(this.name());
        author.setDateofbirth(this.dateofbirth());
        author.setNationality(this.nationality());
        return author;
    }
}
