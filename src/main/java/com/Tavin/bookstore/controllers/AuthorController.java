package com.Tavin.bookstore.controllers;

import com.Tavin.bookstore.dtos.Authors.AuthorResponseDto;
import com.Tavin.bookstore.dtos.Authors.AuthorsRequestDto;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.service.Author.AuthorService;
import jakarta.servlet.ServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addAuthor(@RequestBody AuthorsRequestDto authorsRequest, ServletResponse servletResponse) {
        AuthorModel author = authorsRequest.mappedAuthors();
        service.Save(author);

    URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> GetByDetails(@PathVariable String id) {
        Optional<AuthorModel> optionalAuthor = service.findById(UUID.fromString(id));
        if(optionalAuthor.isPresent()) {
            AuthorModel author = optionalAuthor.get();
            AuthorResponseDto authorModel = new AuthorResponseDto(
                    author.getId(),
                    author.getName(),
                    author.getDateofbirth(),
                    author.getNationality());
            return ResponseEntity.ok(authorModel);
        }
        return ResponseEntity.notFound().build();
    }
}
