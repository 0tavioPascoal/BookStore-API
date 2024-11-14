package com.Tavin.bookstore.service.book;

import com.Tavin.bookstore.model.BookModel;
import com.Tavin.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }
}
