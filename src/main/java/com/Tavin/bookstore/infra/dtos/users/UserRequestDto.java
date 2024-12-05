package com.Tavin.bookstore.infra.dtos.users;

import com.Tavin.bookstore.model.RolesModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequestDto(
        @NotBlank(message = "required field")
        @Size(max = 100)
        String login,

        @NotBlank(message = "required field")
        String password,

        @Email
        String email,

        @NotBlank(message = "required field")
        @Size(max = 100)
        String username,

        @NotNull(message = "required field")
        RolesModel roles) {
}
