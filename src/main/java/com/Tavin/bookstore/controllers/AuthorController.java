package com.Tavin.bookstore.controllers;

import com.Tavin.bookstore.infra.dtos.Authors.AuthorResponseDto;
import com.Tavin.bookstore.infra.dtos.Authors.AuthorsRequestDto;
import com.Tavin.bookstore.infra.Errors.ErrorResponse;
import com.Tavin.bookstore.infra.exceptions.duplicateRecordException;
import com.Tavin.bookstore.infra.exceptions.operationNotPermitted;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.service.Author.AuthorService;
import jakarta.validation.Valid;
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

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> addAuthor(@RequestBody @Valid AuthorsRequestDto authorsRequest) {
        try {
            AuthorModel author = authorsRequest.mappedAuthors();
            service.Save(author);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(author.getId()).toUri();

            return ResponseEntity.created(location).build();
        }catch(duplicateRecordException e) {
            var eDto = ErrorResponse.errorConflict(e.getMessage());
            return ResponseEntity.status(eDto.statusCode()).body(eDto);
        }
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
    public ResponseEntity<Object> deleteAuthor(@PathVariable String id) {
        try {
            Optional<AuthorModel> optionalAuthor = service.findById(UUID.fromString(id));

            if(optionalAuthor.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteByAuthor(optionalAuthor.get());
            return ResponseEntity.noContent().build();
        } catch (operationNotPermitted e) {
            var eResponse = ErrorResponse.errorConflict(e.getMessage());
            return ResponseEntity.status(eResponse.statusCode()).body(eResponse);
        }

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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable String id,
                                             @RequestBody @Valid AuthorsRequestDto authorsRequest) {
        try {
            Optional<AuthorModel> optionalAuthor = service.findById(UUID.fromString(id));

            if(optionalAuthor.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var author = optionalAuthor.get();
            author.setName(authorsRequest.name());
            author.setNationality(authorsRequest.nationality());
            author.setDateofbirth(authorsRequest.dateofbirth());
            service.Updated(author);

            return ResponseEntity.noContent().build();
        } catch (duplicateRecordException e){
            var eDto = ErrorResponse.errorConflict(e.getMessage());
            return ResponseEntity.status(eDto.statusCode()).body(eDto);
        }

    }
}
