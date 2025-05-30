package com.example.library.domain.book.service;

import com.example.library.common.base.BaseAbstractService;
import com.example.library.domain.book.converter.BookConverter;
import com.example.library.domain.book.model.*;
import com.example.library.domain.book.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    protected void updateField(BookEntity entity, BookRequest request) {

    }

    /**
     * 검색 필터
     * 1. 카테고리
     * 2. 제목
     * 3. 저자
     * 4. 출판사
     * 5. 출간년도
     * 6. 가격(Minimum ~ Maximum)
     */
    public Page<BookSummaryResponse> searchBook(BookSearchRequest request, Pageable page) {
        Sort.Direction direction = request.asc() == null || request.asc() ? Sort.Direction.ASC : Sort.Direction.DESC;
        String sortBy = request.sortBy() == null ? "publicationAt" : request.sortBy();

        Pageable sortedPage = PageRequest.of(
                page.getPageNumber(),
                page.getPageSize(),
                Sort.by(direction, sortBy)
        );

        return repository.searchBook(
                sortedPage,
                request.category(),
                request.title() == null ? "" : request.title(),
                request.author(),
                request.publisher(),
                request.year(),
                request.minimum(),
                request.maximum()
        );
    }


}
