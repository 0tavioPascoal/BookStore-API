package com.Tavin.bookstore.repository;

import com.Tavin.bookstore.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByLoginAndUsername(String login, String username);

    UserModel findByLogin(String login);
}
