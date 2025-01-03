package com.Tavin.bookstore.repository;

import com.Tavin.bookstore.model.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientsModel, UUID> {
}
