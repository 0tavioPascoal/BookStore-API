package com.Tavin.bookstore.infra.dtos.clients;

import java.util.UUID;

public record ClientResponseDto(

        UUID id,
        String name,
        String birthday,
        String email,
        String phone
) {
}
