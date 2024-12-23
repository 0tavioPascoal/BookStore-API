package com.Tavin.bookstore.controllers.author;


import com.Tavin.bookstore.infra.dtos.authors.AuthorResponseDto;
import com.Tavin.bookstore.infra.dtos.authors.AuthorsRequestDto;
import com.Tavin.bookstore.infra.dtos.books.BookResponseDto;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.auhtor.AuthorMapper;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.GenderModel;
import com.Tavin.bookstore.service.author.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController implements GeneratedHeader {

    private final AuthorService service;
    private final AuthorMapper mapper;


    @PostMapping
    public ResponseEntity<Void> addAuthor(@RequestBody @Valid AuthorsRequestDto authorsRequest) {

        AuthorModel author = mapper.authorModelMapper(authorsRequest);
        service.Save(author);

        URI location = generateURI(author.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> GetByDetails(@PathVariable String id) {
        var idAuthor = UUID.fromString(id);

        return service
                .findById(idAuthor)
                .map(AuthorModel -> {
                    AuthorResponseDto dto = mapper.authorResponseDtoMapper(AuthorModel);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        Optional<AuthorModel> optionalAuthor = service.findById(UUID.fromString(id));

        if (optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteByAuthor(optionalAuthor.get());
        return ResponseEntity.noContent().build();

    }

    @GetMapping()
    public ResponseEntity<Page<AuthorResponseDto>> GetAllBooks(
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "sizePage",defaultValue = "10",required = false)Integer sizePage
    ) {

        Page<AuthorModel> result = service.search(
                name, nationality, page, sizePage);

        Page<AuthorResponseDto> authorResult = result.map(mapper::authorResponseDtoMapper);

        return ResponseEntity.ok(authorResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable String id,
                                               @RequestBody @Valid AuthorsRequestDto authorsRequest) {

        Optional<AuthorModel> optionalAuthor = service.findById(UUID.fromString(id));

        if (optionalAuthor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var author = optionalAuthor.get();
        author.setName(authorsRequest.name());
        author.setNationality(authorsRequest.nationality());
        author.setDateOfBirth(authorsRequest.dateOfBirth());
        service.Updated(author);

        return ResponseEntity.noContent().build();
    }
}
