package com.example.library.domain.book_loans.converter;

import com.example.library.common.base.BaseAbstractConverter;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book_loans.model.BookLoansEntity;
import com.example.library.domain.book_loans.model.BookLoansRequest;
import com.example.library.domain.book_loans.model.BookLoansResponse;
import com.example.library.domain.user.model.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookLoansConverter extends BaseAbstractConverter<BookLoansEntity, BookLoansRequest, BookLoansResponse> {

    @Override
    public BookLoansResponse toResponse(BookLoansEntity bookLoansEntity) {
        return null;
    }

    @Override
    public BookLoansEntity toEntity(BookLoansRequest request) {
        return null;
    }

    public BookLoansEntity toEntity(UserEntity user, BookEntity book) {
        return BookLoansEntity.builder()
                .user(user)
                .book(book)
                .startAt(LocalDateTime.now())
                .build()
                ;
    }

}
