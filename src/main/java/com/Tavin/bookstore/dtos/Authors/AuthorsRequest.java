package com.Tavin.bookstore.dtos.Authors;

import com.Tavin.bookstore.model.AuthorModel;

import java.time.LocalDate;

public record AuthorsRequest(String name,
                             LocalDate dateOfBirth,
                             String nacionality) {

    public AuthorModel mappedAuthor(){
        AuthorModel author = new AuthorModel();
        author.setName(this.name);
        author.setDateofbirth(this.dateOfBirth);
        author.setNationality(this.nacionality);
        return author;
    }
}
