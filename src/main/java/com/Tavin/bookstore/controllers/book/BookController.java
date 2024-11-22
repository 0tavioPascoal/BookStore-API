package com.Tavin.bookstore.controllers.book;

import com.Tavin.bookstore.infra.dtos.books.BookRequestDto;
import com.Tavin.bookstore.infra.errors.ErrorResponse;
import com.Tavin.bookstore.infra.exceptions.DuplicateRecordException;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.book.BookMapper;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController implements GeneratedHeader {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Void> saveBook(@RequestBody @Valid BookRequestDto requestDto) {
        BookModel book = bookMapper.bookModelMapper(requestDto);
        bookService.save(book);

        URI location = generateURI(book.getId());

        return ResponseEntity.created(location).build();
    }
}
