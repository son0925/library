package com.example.library.domain.book.converter;

import com.example.library.common.base.BaseAbstractConverter;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book.model.BookRequest;
import com.example.library.domain.book.model.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends BaseAbstractConverter<BookEntity, BookRequest, BookResponse> {
    @Override
    public BookResponse toResponse(BookEntity bookEntity) {
        return BookResponse.builder()
                .title(bookEntity.getTitle())
                .description(bookEntity.getDescription())
                .price(bookEntity.getPrice())
                .imageUrl(bookEntity.getImageUrl())
                .author(bookEntity.getAuthor())
                .publisher(bookEntity.getPublisher())
                .publicationAt(bookEntity.getPublicationAt())
                .category(bookEntity.getCategory())
                .build()
                ;
    }

    @Override
    public BookEntity toEntity(BookRequest request) {
        return BookEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .price(request.getPrice())
                .category(request.getCategory())
                .publisher(request.getPublisher())
                .publicationAt(request.getPublicationAt())
                .description(request.getDescription())
                .build()
                ;
    }
}
