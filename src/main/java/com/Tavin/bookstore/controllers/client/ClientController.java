package com.Tavin.bookstore.controllers.client;

import ch.qos.logback.core.net.server.Client;
import com.Tavin.bookstore.infra.dtos.clients.ClientsRequestDto;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.clients.ClientMapper;
import com.Tavin.bookstore.model.ClientsModel;
import com.Tavin.bookstore.service.client.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController implements GeneratedHeader {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Void> createClient(@Valid @RequestBody ClientsRequestDto requestDto) {
        ClientsModel clients = clientMapper.clientsModelMapper(requestDto);
        clientService.save(clients);

        URI location = generateURI(clients.getId());

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteClient(@RequestParam String id) {
        return clientService.findById(UUID.fromString(id))
                .map(ClientsModel -> {
                    clientService.delete(ClientsModel);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping()
    public ResponseEntity<Object> updateClient(@RequestParam String id,@Valid @RequestBody ClientsRequestDto requestDto) {
        return clientService.findById(UUID.fromString(id))
                .map(ClientsModel -> {
                  ClientsModel clientsAux = clientMapper.clientsModelMapper(requestDto);
                   ClientsModel.setClientSecret(clientsAux.getClientSecret());
                   ClientsModel.setRedirectUri(clientsAux.getRedirectUri());
                   ClientsModel.setScope(clientsAux.getScope());
                   clientService.save(ClientsModel);
                   return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }
}

