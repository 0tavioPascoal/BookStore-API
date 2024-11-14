package com.Tavin.bookstore.infra.dtos.books;

import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.GenderModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRequestDto(@NotBlank(message = "required field")
                                @Size(max = 150, message = "field outside the standard size")
                             String title,
                             @NotNull(message = "required field")
                                     @Past(message = "It cannot be a future date")
                             LocalDate publicationDate,
                             GenderModel gender,
                             BigDecimal price,
                             @NotNull(message = "required field")
                             UUID idAuthor) {

    public BookModel BookMappedRequestDto() {
        BookModel book = new BookModel();
        book.setTitle(this.title);
        book.setPublicationDate(this.publicationDate);
        book.setGender(this.gender);
        book.setPrice(this.price);
        book.setId(this.idAuthor);
        return book;
    }
}
