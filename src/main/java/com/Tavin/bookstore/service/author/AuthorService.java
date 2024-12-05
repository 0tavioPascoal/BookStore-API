package com.Tavin.bookstore.service.author;


import com.Tavin.bookstore.infra.exceptions.OperationNotPermitted;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.model.UserModel;
import com.Tavin.bookstore.repository.AuthorRepository;
import com.Tavin.bookstore.repository.BookRepository;
import com.Tavin.bookstore.infra.dtos.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class AuthorService {


    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;


    public AuthorModel Save(AuthorModel authorModel) {
        validator.validateAuthor(authorModel);
        return repository.save(authorModel);}

    public void Updated(AuthorModel authorModel) {
        if(authorModel.getId() == null) {
            throw new IllegalArgumentException("To update, the author must already be registered");
        }
        validator.validateAuthor(authorModel);
        repository.save(authorModel);
    }

    public Optional<AuthorModel> findById(UUID id) {
        return repository.findById(id);
    }

    public void deleteByAuthor(AuthorModel author){
        if(ownBook(author)){
            throw new OperationNotPermitted("It is not allowed to delete an author with a registered book");
        }
        repository.delete(author);
    }

    public List<AuthorModel> search(String name, String nationality) {
       AuthorModel author = new AuthorModel();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<AuthorModel> example = Example.of(author, exampleMatcher);
        return repository.findAll(example);
    }

    public boolean ownBook(AuthorModel author){
        return bookRepository.existsByAuthor(author);
   }
}
