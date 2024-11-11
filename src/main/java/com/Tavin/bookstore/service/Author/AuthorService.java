package com.Tavin.bookstore.service.Author;

import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {


    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public AuthorModel Save(AuthorModel authorModel) {
        return repository.save(authorModel);}

    public Optional<AuthorModel> findById(UUID id) {
        return repository.findById(id);
    }
    }
