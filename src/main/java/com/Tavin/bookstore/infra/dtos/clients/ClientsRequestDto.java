package com.Tavin.bookstore.infra.dtos.clients;

public record ClientsRequestDto(String clientId,
                                String clientSecret,
                                String redirectUri,
                                String scope) {
}
