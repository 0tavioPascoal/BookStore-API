package com.Tavin.bookstore.controllers.book;

import com.Tavin.bookstore.infra.dtos.authors.AuthorResponseDto;
import com.Tavin.bookstore.infra.dtos.books.BookRequestDto;
import com.Tavin.bookstore.infra.dtos.books.BookResponseDto;
import com.Tavin.bookstore.infra.errors.ErrorResponse;
import com.Tavin.bookstore.infra.exceptions.DuplicateRecordException;
import com.Tavin.bookstore.infra.header.GeneratedHeader;
import com.Tavin.bookstore.infra.mappers.book.BookMapper;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.GenderModel;
import com.Tavin.bookstore.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
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

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBooks(
            @PathVariable String id) {

        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    var dto = bookMapper.bookResponseDtoMapper(book);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteBook(
            @RequestParam String id) {

        return bookService.findById(UUID.fromString(id))
                .map(bookModel -> {
                    bookService.deleteBook(bookModel);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping()
    public ResponseEntity<Page<BookResponseDto>> GetAllBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "price", required = false)BigDecimal price,
            @RequestParam(value = "gender", required = false)GenderModel gender,
            @RequestParam(value = "publicationDate", required = false)LocalDate publicationDate,
            @RequestParam(value = "nameAuthor", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "sizePage",defaultValue = "10",required = false)Integer sizePage
            ) {

        Page<BookModel> result = bookService.search(
                title, publicationDate, gender, price, name, page, sizePage);

        Page<BookResponseDto> bookResult = result.map(bookMapper::bookResponseDtoMapper);

        return ResponseEntity.ok(bookResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@RequestBody @Valid BookRequestDto requestDto
    , @PathVariable String id) {
        return bookService.findById(UUID.fromString(id))
                .map( bookModel -> {
                   BookModel bookAux = bookMapper.bookModelMapper(requestDto);
                   bookModel.setPublicationDate(bookAux.getPublicationDate());
                   bookModel.setTitle(bookAux.getTitle());
                   bookModel.setPrice(bookAux.getPrice());
                   bookModel.setGender(bookAux.getGender());
                   bookAux.setAuthor(bookAux.getAuthor());
                   bookService.updatedBook(bookModel);
                   return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());

    }
}
