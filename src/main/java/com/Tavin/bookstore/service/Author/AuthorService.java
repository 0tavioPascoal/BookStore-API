package com.Tavin.bookstore.service.Author;

import com.Tavin.bookstore.exceptions.operationNotPermitted;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.repository.AuthorRepository;
import com.Tavin.bookstore.repository.BookRepository;
import com.Tavin.bookstore.validator.AuthorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {


    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;


    public AuthorService(AuthorRepository repository, AuthorValidator validator, BookRepository bookRepository) {
        this.repository = repository;
        this.validator = validator;
        this.bookRepository = bookRepository;
    }

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
            throw new operationNotPermitted("It is not allowed to delete an author with a registered book");
        }
        repository.delete(author);
    }

    public List<AuthorModel> search(String name, String nationality) {
      if(name != null && nationality != null) {
        return repository.findByNameAndNationality(name, nationality);
      }
      if(name != null){
          return repository.findByName(name);
      }

      if(nationality != null){
          return repository.findByNationality(nationality);
      }

      return repository.findAll();
    }

    public boolean ownBook(AuthorModel author){
        return bookRepository.existsByAuthor(author);
   }
}
