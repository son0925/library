package com.example.library.domain.book_loans.converter;

import com.example.library.common.base.BaseAbstractConverter;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book_loans.model.BookLoansEntity;
import com.example.library.domain.book_loans.model.BookLoansInfoResponse;
import com.example.library.domain.book_loans.model.BookLoansRequest;
import com.example.library.domain.book_loans.model.BookLoansResponse;
import com.example.library.domain.user.model.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookLoansConverter extends BaseAbstractConverter<BookLoansEntity, BookLoansRequest, BookLoansResponse> {

    @Override
    public BookLoansResponse toResponse(BookLoansEntity bookLoansEntity) {
        return BookLoansResponse.builder()
                .id(bookLoansEntity.getId())
                .bookName(bookLoansEntity.getBook().getTitle())
                .description("성공")
                .build()
                ;
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

    public List<BookLoansInfoResponse> toInfoResponseList(List<BookLoansEntity> bookLoansEntityList) {
        return bookLoansEntityList.stream()
                .map(this::toInfoResponse)
                .toList()
                ;
    }

    private BookLoansInfoResponse toInfoResponse(BookLoansEntity entity) {
        return BookLoansInfoResponse.builder()
                .title(entity.getBook().getTitle())
                .bookImageUrl(entity.getBook().getImageUrl())
                .category(entity.getBook().getCategory())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getName())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .build()
                ;
    }
}
