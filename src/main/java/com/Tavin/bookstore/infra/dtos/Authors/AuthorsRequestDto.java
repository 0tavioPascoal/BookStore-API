package com.Tavin.bookstore.infra.dtos.Authors;

import com.Tavin.bookstore.model.AuthorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorsRequestDto(UUID id,
                                @NotBlank(message = "required field")
                                String name,
                                @NotNull(message = "required field")
                                LocalDate dateofbirth,
                                @NotBlank(message = "required field")
                                String nationality) {

    public AuthorModel mappedAuthors(){
        AuthorModel author = new AuthorModel();
        author.setName(this.name());
        author.setDateofbirth(this.dateofbirth());
        author.setNationality(this.nationality());
        return author;
    }
}
