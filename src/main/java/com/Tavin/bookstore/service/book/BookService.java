package com.Tavin.bookstore.service.book;

import com.Tavin.bookstore.infra.dtos.authors.AuthorResponseDto;
import com.Tavin.bookstore.infra.dtos.books.BookResponseDto;
import com.Tavin.bookstore.infra.mappers.book.BookMapper;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.GenderModel;
import com.Tavin.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public Optional<BookModel> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(BookModel bookModel) {
         bookRepository.delete(bookModel);
    }

    public List<BookModel> search(String title, LocalDate publicationDate, GenderModel gender, BigDecimal price, String name) {
        BookModel bookModel = new BookModel();
        bookModel.setTitle(title);
        bookModel.setGender(gender);
        bookModel.setPrice(price);
        bookModel.setPublicationDate(publicationDate);
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<BookModel> example = Example.of(bookModel, matcher);
        return bookRepository.findAll(example);
    }

}
