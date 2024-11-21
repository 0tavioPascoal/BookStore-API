package com.Tavin.bookstore.infra.dtos.authors;

import com.Tavin.bookstore.model.AuthorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorsRequestDto(UUID id,
                                @NotBlank(message = "required field")
                                        @Size(max = 100,
                                        message = "field outside the standard size")
                                String name,
                                @NotNull(message = "required field")
                                        @Past(message = "It cannot be a future date")
                                LocalDate dateOfBirth,
                                @NotBlank(message = "required field")
                                        @Size(max = 50, min = 2,
                                                message = "field outside the standard size")
                                String nationality) {

}
