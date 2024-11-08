package com.Tavin.bookstore.controllers;

import com.Tavin.bookstore.dtos.Authors.AuthorsRequest;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.service.Author.AuthorService;
import jakarta.servlet.ServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addAuthor(@RequestBody AuthorsRequest authorsRequest, ServletResponse servletResponse) {
        AuthorModel author = authorsRequest.mappedAuthor();
        service.Save(author);

    URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
