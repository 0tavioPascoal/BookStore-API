package com.Tavin.bookstore.infra.dtos.clients;

public record ClientsResponseDto(String clientId,
                                 String clientSecret,
                                 String redirectUri,
                                 String scope) {
}
