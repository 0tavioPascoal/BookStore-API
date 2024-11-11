package com.Tavin.bookstore.repository;

import com.Tavin.bookstore.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

    List<AuthorModel> findByName(String name);

    List<AuthorModel> findByNationality(String nationality);

    List<AuthorModel> findByNameAndNationality(String name,String nationality);
}
