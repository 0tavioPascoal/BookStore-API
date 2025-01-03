package com.Tavin.bookstore.infra.dtos.clients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientRequestDto(
        @NotBlank
        String name,

        @NotNull
        LocalDate birth,

        @Email
        String email,

        @NotBlank
        String phone) {
}
