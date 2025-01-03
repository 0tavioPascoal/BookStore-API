package com.Tavin.bookstore.infra.dtos.clients;

import java.time.LocalDate;
import java.util.UUID;

public record ClientResponseDto(

        UUID id,
        String name,
        LocalDate birth,
        String email,
        String phone
) {
}
