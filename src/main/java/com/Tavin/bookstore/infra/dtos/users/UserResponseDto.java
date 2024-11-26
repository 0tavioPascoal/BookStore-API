package com.Tavin.bookstore.infra.dtos.users;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String login,
        String email,
        String username,
        List<String> roles) {
}
