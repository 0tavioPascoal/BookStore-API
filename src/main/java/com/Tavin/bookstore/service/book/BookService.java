package com.Tavin.bookstore.service.book;

import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public Optional<BookModel> findById(UUID id) {
        return bookRepository.findById(id);
    }
}
