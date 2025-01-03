package com.Tavin.bookstore.service.client;

import com.Tavin.bookstore.model.ClientsModel;
import com.Tavin.bookstore.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void save(ClientsModel clientsModel) {
        clientRepository.save(clientsModel);
    }

    public Optional<ClientsModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public void delete(ClientsModel clientsModel) {
        clientRepository.delete(clientsModel);
    }
}
