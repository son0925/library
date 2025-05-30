package com.example.library.domain.book.repository;

import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book.model.BookSummaryResponse;
import com.example.library.domain.book.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("""
            SELECT DISTINCT new com.example.library.domain.book.model.BookSummaryResponse(
                b.title, b.author, b.publisher, b.price, b.publicationAt
            )
            FROM BookEntity b
            WHERE (:category IS NULL OR b.category = :category)
            AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))
            AND (:publisher IS NULL OR LOWER(b.publisher) LIKE LOWER(CONCAT('%', :publisher, '%')))
            AND(:year IS NULL OR FUNCTION('YEAR', b.publicationAt) = :year)
            AND (:minimum IS NULL OR b.price >= :minimum)
            AND (:maximum IS NULL OR b.price <= :maximum)
            """)
    Page<BookSummaryResponse> searchBook(Pageable page,
                                         @Param("category") Category category,
                                         @Param("title") String title,
                                         @Param("author") String author,
                                         @Param("publisher") String publisher,
                                         @Param("year") Integer year,
                                         @Param("minimum") Integer minimum,
                                         @Param("maximum") Integer maximum
    );
}
