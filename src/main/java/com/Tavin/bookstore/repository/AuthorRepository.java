package com.Tavin.bookstore.repository;

import com.Tavin.bookstore.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

    List<AuthorModel> findByName(String name);

    List<AuthorModel> findByNationality(String nationality);

    List<AuthorModel> findByNameAndNationality(String name,String nationality);

    Optional<AuthorModel> findByNameAndDateofbirthAndNationality(String name,
                                                                 LocalDate dateofbirth,
                                                                 String nationality);

    void deleteById(AuthorModel author);
}
