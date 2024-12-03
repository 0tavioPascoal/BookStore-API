package com.Tavin.bookstore.service.client;

import com.Tavin.bookstore.model.ClientsModel;
import com.Tavin.bookstore.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientsModel save(ClientsModel clientsModel) {
        var pass = passwordEncoder.encode(clientsModel.getClientSecret());
        clientsModel.setClientSecret(pass);
        return clientRepository.save(clientsModel);
    }

    public ClientsModel findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }

    public void delete(ClientsModel clientsModel) {
        clientRepository.delete(clientsModel);
    }

    public Optional<ClientsModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public void update(ClientsModel clients) {
        if (clients.getId() == null) {
            throw new IllegalArgumentException("\"To update, the User must already be registered");
        }
        clientRepository.save(clients);
    }
}
