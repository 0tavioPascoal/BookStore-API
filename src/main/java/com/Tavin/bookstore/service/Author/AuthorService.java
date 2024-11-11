package com.Tavin.bookstore.service.Author;

import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deleteById(UUID id) {
        repository.deleteById(id);
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
}
