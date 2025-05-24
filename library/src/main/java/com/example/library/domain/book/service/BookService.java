package com.example.library.domain.book.service;

import com.example.library.common.base.BaseAbstractService;
import com.example.library.domain.book.converter.BookConverter;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book.model.BookRequest;
import com.example.library.domain.book.model.BookResponse;
import com.example.library.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends BaseAbstractService<
        BookEntity,
        BookRequest,
        BookResponse,
        BookRepository,
        BookConverter
        > {

    protected BookService(BookRepository repository, BookConverter converter) {
        super(repository, converter);
    }
}
