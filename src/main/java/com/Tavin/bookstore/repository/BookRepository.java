package com.Tavin.bookstore.repository;

import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.GenderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID>, JpaSpecificationExecutor<BookModel> {

    boolean existsByAuthor(AuthorModel author);

    Optional<BookModel> findByTitleAndPublicationDateAndPriceAndGenderAndAuthor(String title,
                                                                                  LocalDate publicationDate,
                                                                                  BigDecimal price,
                                                                                  GenderModel gender,
                                                                                  AuthorModel author);

}
