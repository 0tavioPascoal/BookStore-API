package com.Tavin.bookstore.controllers.clients;

import com.Tavin.bookstore.infra.dtos.books.BookResponseDto;
import com.Tavin.bookstore.infra.dtos.clients.ClientRequestDto;
import com.Tavin.bookstore.infra.dtos.clients.ClientResponseDto;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.client.ClientMapper;
import com.Tavin.bookstore.model.ClientsModel;
import com.Tavin.bookstore.service.client.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController implements GeneratedHeader {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody @Valid ClientRequestDto requestDto) {
        ClientsModel client = clientMapper.clientsModelMapper(requestDto);
        clientService.save(client);
        URI location = generateURI(client.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping()
    public ResponseEntity<ClientResponseDto> getClient(
            @RequestParam String id) {

        return clientService.findById(UUID.fromString(id))
                .map(client -> {
                    var dto = clientMapper.clientResponseDtoMapper(client);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
