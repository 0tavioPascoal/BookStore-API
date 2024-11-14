package com.Tavin.bookstore.infra.dtos.validator;

import com.Tavin.bookstore.infra.exceptions.duplicateRecordException;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private final AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void validateAuthor(AuthorModel author) {
        if(existsRegisteredAuthor(author)) {
            throw new duplicateRecordException("author already registered");
        }
    }

    private boolean existsRegisteredAuthor(AuthorModel author){
        Optional<AuthorModel> authorExists = repository.findByNameAndDateofbirthAndNationality(
                author.getName(), author.getDateofbirth(), author.getNationality()
        );
        if(author.getId() == null ){
            return authorExists.isPresent();
        }

        return !author.getId().equals(authorExists.get().getId()) && authorExists.isPresent();
    }

}
