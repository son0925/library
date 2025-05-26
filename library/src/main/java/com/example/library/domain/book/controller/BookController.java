package com.example.library.domain.book.controller;

import com.example.library.common.base.BaseAbstractController;
import com.example.library.domain.book.converter.BookConverter;
import com.example.library.domain.book.model.*;
import com.example.library.domain.book.repository.BookRepository;
import com.example.library.domain.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
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

    @GetMapping("/search")
    public Page<BookResponse> searchBook(
            BookSearchRequest bookSearchRequest,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return service.searchBook(bookSearchRequest, pageable);
    }
}
