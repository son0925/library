package com.example.library.domain.book.controller;

import com.example.library.common.base.BaseAbstractController;
import com.example.library.domain.book.converter.BookConverter;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book.model.BookRequest;
import com.example.library.domain.book.model.BookResponse;
import com.example.library.domain.book.repository.BookRepository;
import com.example.library.domain.book.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController extends BaseAbstractController<
        BookEntity,
        BookRequest,
        BookResponse,
        BookRepository,
        BookService,
        BookConverter> {

    public BookController(BookService service) {
        super(service);
    }
}
