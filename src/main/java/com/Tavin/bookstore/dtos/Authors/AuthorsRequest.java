package com.Tavin.bookstore.dtos.Authors;

import com.Tavin.bookstore.model.AuthorModel;

import java.time.LocalDate;

public record AuthorsRequest(String name,
                             LocalDate dataNascimento,
                             String nacionalidade) {

    public AuthorModel mappedAuthor(){
        AuthorModel author = new AuthorModel();
        author.setName(this.name);
        author.setNacionalidade(this.nacionalidade);
        author.setDataNascimento(this.dataNascimento);
        return author;
    }
}
