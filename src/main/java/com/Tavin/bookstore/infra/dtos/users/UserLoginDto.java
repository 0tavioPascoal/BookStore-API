package com.Tavin.bookstore.infra.dtos.users;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;


public record UserLoginDto(
        @NotBlank
                @Size(min = 1, max = 50)
        String login,

        @NotBlank
                @Size(min = 1, max = 50)
        String password) {
}
