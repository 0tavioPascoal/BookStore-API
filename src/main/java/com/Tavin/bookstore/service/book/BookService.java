package com.Tavin.bookstore.service.book;

import com.Tavin.bookstore.infra.mappers.book.BookMapper;
import com.Tavin.bookstore.model.AuthorModel;
import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.model.GenderModel;
import com.Tavin.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    public Page<BookModel> search(String title, LocalDate publicationDate, GenderModel gender, BigDecimal price, String name, Integer page, Integer pageSize) {
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

        Pageable pageRequest = PageRequest.of(page, pageSize);
        return bookRepository.findAll(example, pageRequest);
    }

    public void updatedBook(BookModel bookModel) {
        if(bookModel.getId() == null) {
            throw new IllegalArgumentException("To update, the book must already be registered");
        }

        bookRepository.save(bookModel);
    }

}
