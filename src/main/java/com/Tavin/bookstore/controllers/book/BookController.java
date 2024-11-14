package com.Tavin.bookstore.controllers.book;

import com.Tavin.bookstore.infra.dtos.books.BookRequestDto;
import com.Tavin.bookstore.infra.errors.ErrorResponse;
import com.Tavin.bookstore.infra.exceptions.duplicateRecordException;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody @Valid BookRequestDto requestDto) {
        try{
            BookModel book = requestDto.BookMappedRequestDto();
            bookService.save(book);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(book.getId()).toUri();

          return ResponseEntity.created(location).build();

        } catch (duplicateRecordException e) {
            var eDto = ErrorResponse.errorConflict(e.getMessage());
            return ResponseEntity.status(eDto.statusCode()).body(eDto);
        }


    }
}
