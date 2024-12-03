package com.Tavin.bookstore.repository;

import com.Tavin.bookstore.model.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientsModel, UUID> {

    ClientsModel findByClientId(String clientId);

}
