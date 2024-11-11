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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        Optional<AuthorModel> optionalAuthor = service.findById(UUID.fromString(id));

        if(optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(optionalAuthor.get().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<AuthorResponseDto>> GetAllAuthors(
                @RequestParam(value = "name", required = false) String name,
                @RequestParam(value = "nationality", required = false) String nationality) {

        List<AuthorModel> result = service.search(name, nationality);
        List<AuthorResponseDto> authors = result.stream().map(author -> new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getDateofbirth(),
                author.getNationality())
        ).collect(Collectors.toList());

        return ResponseEntity.ok(authors);
    }
}
